package com.jt.controller;

import com.jt.pojo.User;
import com.jt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController   //@Controller 将该类交给Spring容器管理 +//@ResponseBody 业务返回值时,将数据转化为JSON.
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 需求: 查询全部user表数据
     * 请求类型: get/post/put/delete
     * 路径: /findUser
     * 参数: 无
     * 返回值: List<User>
     */
    @RequestMapping("/getUser")
    public List<User> findUser(){

        return userService.findAll();
    }

}
