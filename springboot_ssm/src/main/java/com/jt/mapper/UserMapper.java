package com.jt.mapper;

import com.jt.pojo.User;

import java.util.List;

public interface UserMapper {
    //查询demo_user表中的所有数据
    List<User> findAll();
}
