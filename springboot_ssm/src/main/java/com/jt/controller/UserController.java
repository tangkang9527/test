package com.jt.controller;

import com.jt.pojo.User;
import com.jt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 业务: 根据ID查询用户数据.
     * 请求类型: get
     * URL:http://localhost:8090/findUserById?id=1
     * 参数: id=1
     * 返回值: User对象
     * SpringMVC业务规范:
     *      1.接收参数时,必须与用户参数保持一致.
     */
    //@RequestMapping(value = "findUserById",method = RequestMethod.GET)
    @GetMapping("findUserById") //只能接收Get请求类型
    public User findUserById(Integer id){

        return userService.findUserById(id);
    }



}
