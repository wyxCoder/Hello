package org.demo.dao;

import org.apache.ibatis.annotations.*;
import org.demo.model.User;

/**
 * Created by yuxuan on 2017/11/27.
 */
@Mapper
public interface UserDAO {
    // 注意空格
    String TABLE_NAME = " user ";
    String INSERT_FIELDS = " name, password";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{name},#{password})"})
    int addUser(User user);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where name=#{name}"})
    User selectByName(String name);

    @Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
    void deleteById(int id);

    void updatePasswd(User user);
}
