package com.jt.controller;

import com.jt.pojo.User;
import com.jt.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@CrossOrigin      //解决跨域问题的
@RestController   //@Controller 将该类交给Spring容器管理 +//@ResponseBody 业务返回值时,将数据转化为JSON.
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUser")
    public List<User> findUser(){

        return userService.findAll();
    }

}
