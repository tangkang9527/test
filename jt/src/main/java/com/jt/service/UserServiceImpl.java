package com.jt.service;

import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    //根据u/p查询数据库
    @Override
    public String findUserByUP(User user) {
        //1.将密码加密
        byte[] bytes = user.getPassword().getBytes();
        String md5Pass = DigestUtils.md5DigestAsHex(bytes);
        System.out.println(md5Pass);
        user.setPassword(md5Pass);
        //2.根据用户名和密文查询数据库
        User userDB = userMapper.findUserByUP(user);
        //3.判断userDB是否有值
        if(userDB == null){
            //查询没有数据.返回null
            return null;
        }
        //秘钥特点: 唯一性,迷惑性  UUID:几乎可以保证唯一性
        return UUID.randomUUID().toString().replace("-","");
    }

    @Override
    public PageResult getUserListByPage(PageResult pageResult) {
        return null;
    }
}
