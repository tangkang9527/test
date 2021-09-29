package com.jt.controller;

import com.jt.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringMVCController {

    /**
     * URL:http://localhost:8090/mvc?name="李四"&age=18&sex="女"
     * 说明: 如果使用对象的方式进行接收,则必须有Setxx方法.
     * @return
     */
    @GetMapping("/mvc")
    public Object testDemo(User user){

        return "参数名称:"+user;
    }

}
