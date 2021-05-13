package com.ybourne.project.service;

import com.ybourne.project.dao.TicketDAO;
import com.ybourne.project.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    @Autowired
    private TicketDAO ticketDAO;

    public Ticket getTicket(String t) { return ticketDAO.selectByTicket(t); }

    public Ticket getTicket(int uid) { return ticketDAO.selectById(uid); }

    public void addTicket(Ticket ticket) { ticketDAO.addTicket(ticket); }

    public void deleteTicket(String t) { ticketDAO.deleteTicket(t);}

    public void deleteTicket(int id) { ticketDAO.deleteTicketById(id); }
}
