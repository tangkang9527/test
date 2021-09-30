package com.jt.service;

import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service    //将该类交给Spring容器管理
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper; //代理对象 JDK动态代理

    @Override
    public List<User> findAll() {
        //List<User> userList = userMapper.findAll();
        //return userList;
        return userMapper.findAll();
    }

    @Override
    public User findUserById(Integer id) {

        return userMapper.findUserById(id);
    }


    @Override
    public void update(User user) {

        userMapper.update(user);
    }

    @Override
    public List<User> findUserByAS(User user) {

        return userMapper.findUserByAS(user);
    }

    @Override
    public List<User> findUserBYNA(User user) {

        return userMapper.findUserByNA(user);
    }

    @Override
    public List<User> findUserByLike(String name) {

        return userMapper.findUserByLike(name);
    }

    @Override
    public List<User> findUserByIds(Integer[] id) {

        return userMapper.findUserByIds(id);
    }

    @Override
    public List<User> findUser(User user) {

        return userMapper.findUser(user);
    }

    @Override
    public List<User> findUserByNS(User user) {
        return userMapper.findUserByNS(user);
    }

    //框架整合mybatis之后,数据自动提交.
    @Override
    public void saveUser(User user) {
        userMapper.saveUser(user);
    }

    @Override
    public void updateUser(User user) {

        userMapper.updateUser(user);
    }
}
