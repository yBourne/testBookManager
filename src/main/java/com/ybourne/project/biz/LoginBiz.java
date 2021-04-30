package com.ybourne.project.biz;

import com.ybourne.project.model.Ticket;
import com.ybourne.project.model.User;
import com.ybourne.project.model.exceptions.LoginException;
import com.ybourne.project.service.TicketService;
import com.ybourne.project.service.UserService;
import com.ybourne.project.util.ConcurrentUtils;
import com.ybourne.project.util.MD5;
import com.ybourne.project.util.TicketUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginBiz {
    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    /**
     *先检查邮箱、密码，再更新t票
     * @return 返回最新t票
     * @throws Exception 账号密码错误
     */
    public String login(String email, String password){
        User user = userService.getUser(email);

        if (user==null) throw new LoginException("No email address!");
        else if (user.getPassword().equals(password)) throw new LoginException("Password denied!");

        Ticket t = ticketService.getTicket(user.getId());
        if (t==null){
            Ticket ticket = TicketUtils.next(user.getId());
            ticketService.addTicket(ticket);
            return ticket.getTicket();
        }

        if (t.getExpiredAt().before(new Date())){
            ticketService.deleteTicket(t.getId());
        }

        t = TicketUtils.next(user.getId());
        ticketService.addTicket(t);

        ConcurrentUtils.setHost(user);
        return t.getTicket();
    }

    /**
     * 登出，直接删除数据库中ticket
     * @param t
     */
    public void logOut(String t) { ticketService.deleteTicket(t);}

    /**
     * 注册用户，返回产生的ticket
     */
    public String register(User user){
        if (userService.getUser(user.getEmail())!=null){
            throw new LoginException("User exists!");
        }

        String password = MD5.next(user.getPassword());
        user.setPassword(password);
        userService.addUser(user);

        Ticket ticket = TicketUtils.next(user.getId());
        ticketService.addTicket(ticket);

        ConcurrentUtils.setHost(user);
        return ticket.getTicket();
    }
}
