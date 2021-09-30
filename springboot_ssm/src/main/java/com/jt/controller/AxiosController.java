package com.jt.controller;

import com.jt.pojo.User;
import com.jt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/axios")
public class AxiosController {

    @Autowired
    private UserService userService;

    /**
     * 根据ID查询用户信息
     * URL: http://localhost:8090/axios/findUserById?id=1
     */
    @GetMapping("/findUserById")
    public User findUserById(Integer id){

        return userService.findUserById(id);
    }


    /**
     * 根据age和sex查询数据
     * URL地址:http://localhost:8090/axios/findUserByAS?age=18&sex=%E5%A5%B3
     * 请求类型: get
     * 参数: age/sex
     * 返回值: list<User>
     */
    @GetMapping("/findUserByAS")
    public List<User> findUserByAS(User user){

        return userService.findUserByAS(user);
    }

    /**
     * 业务说明: 接收restFul请求
     * URL:http://localhost:8090/axios/user/${name}/${sex}
     * 参数: name/sex
     * 结果: List<User>
     */
    @GetMapping("/user/{name}/{sex}")
    public List<User> findUserByNS(User user){

        return userService.findUserByNS(user);
    }
}
