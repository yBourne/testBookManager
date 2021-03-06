package com.ybourne.project.dao;

import com.ybourne.project.model.Ticket;
import com.ybourne.project.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDAO {
    String table_name = " ticket ";
    String insert_field = " user_id, ticket, expired_at ";
    String select_field = " id, " + insert_field;

    @Insert({"insert into", table_name, "(", insert_field,
            ") values (#{name},#{email},#{password})"})
    int addUser(User user);

    @Select({"select", select_field, "from", table_name, "where id=#{id}"})
    User selectById(int id);

    @Select({"select", select_field, "from", table_name, "where name=#{name}"})
    User selectByName(String name);

    @Select({"select", select_field, "from", table_name, "where email=#{email}"})
    User selectByEmail(String email);

    @Update({"update", table_name, "set password=#{password} where id=#{id}"})
    void updatePassword(User user);
}
