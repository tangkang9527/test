package com.jt.mapper;

import com.jt.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    List<User> findAll();
    @Select("select * from user where username=#{username} and password=#{password}")
    User findUserByUP(User user);
}
