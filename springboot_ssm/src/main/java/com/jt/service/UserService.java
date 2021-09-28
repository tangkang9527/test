package com.jt.service;

import com.jt.pojo.User;

import java.util.List;

public interface UserService {

    //查询user表中的所有的数据
    List<User> findAll();

}
