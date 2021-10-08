package com.jt.controller;

import com.jt.pojo.User;
import com.jt.service.UserService;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findAll")
    public List<User> findAll(){

        return userService.findAll();
    }

    /**
     * 需求: 根据u/p查询数据库,返回秘钥token
     * URL: /user/login
     * 类型: post
     * 参数: username/password json
     * 返回值: SysResult对象(token)
     */
    @PostMapping("/login")
    public SysResult login(@RequestBody User user){

        String token = userService.findUserByUP(user);
        if(token == null || "".equals(token)){
            //表示用户名和密码错误
            return SysResult.fail();
        }
        //表示用户名和密码正确,返回秘钥信息
        return SysResult.success(token);
    }



}
