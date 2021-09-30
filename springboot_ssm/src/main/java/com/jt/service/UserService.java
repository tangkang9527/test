package com.jt.service;

import com.jt.pojo.User;

import java.util.List;

public interface UserService {

    //查询user表中的所有的数据
    List<User> findAll();

    User findUserById(Integer id);

    void update(User user);

    List<User> findUserByAS(User user);

    List<User> findUserBYNA(User user);

    List<User> findUserByLike(String name);

    List<User> findUserByIds(Integer[] id);

    List<User> findUser(User user);

    List<User> findUserByNS(User user);

    void saveUser(User user);

    void updateUser(User user);
}
