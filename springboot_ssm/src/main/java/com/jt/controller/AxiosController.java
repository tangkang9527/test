package com.jt.controller;

import com.jt.pojo.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/axios")
public class AxiosController {

    /**
     * 根据ID查询用户信息
     * URL: http://localhost:8090/axios/findUserById
     */
    @GetMapping("/findUserById")
    public User findUserById(Integer id){

        return null;
    }
}
