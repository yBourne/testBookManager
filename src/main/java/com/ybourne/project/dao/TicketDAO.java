package com.ybourne.project.dao;

import com.ybourne.project.model.Ticket;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TicketDAO {

    String table_name = " ticket ";
    String insert_field  = " user_id, ticket, expired_at ";
    String select_field = " id, " + insert_field;

    @Insert({"insert into", table_name, "(", insert_field,
    ") values (#{userId},#{ticket},#{expiredAt}"})
    int addTicket(Ticket ticket);

    @Select({"select", select_field, "from", table_name,
    "where userId=#{uid}"})
    Ticket selectById(int uid);

    @Select({"select", select_field, "from", table_name,
    "where ticket=#{t}"})
    Ticket selectByTicket(String t);

    @Delete({"delete from", table_name, "where id=#{tid}"})
    void deleteTicketById(int tid);

    @Delete({"delete from", table_name, "where ticket=#{tid}"})
    void deleteTicket(String t);
}
