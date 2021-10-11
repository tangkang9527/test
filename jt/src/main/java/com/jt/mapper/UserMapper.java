package com.jt.mapper;

import com.jt.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    List<User> findAll();
    @Select("select * from user where username=#{username} and password=#{password}")
    User findUserByUP(User user);
    @Select("SELECT COUNT(1) FROM user")
    long findTotal();
    //将多值封装为单值  一般使用对象/集合/Map
    List<User> findUserListByPage(@Param("start") int start,
                                  @Param("size") int size,
                                  @Param("query") String query);
}
