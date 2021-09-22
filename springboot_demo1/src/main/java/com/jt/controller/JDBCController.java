package com.jt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 说明:
 *  1.将该类交给Spring容器管理
 *  2.SpringMVC负责调用该对象接收用户的请求.
 *  3.将业务处理之后的结果,为页面返回JSON数据.
 *  @ResponseBody作用: 将数据转化为JSON串
 *
 *  propertySource:  value属性指定路径
 *                   encoding属性指定配置文件编码格式
 */
@RestController
@PropertySource(value="classpath:/mysql.properties",encoding = "UTF-8")
public class JDBCController {
    //${key} Spring提供的springel表达式 简称为:spel表达式
    //语法: 从spring容器内部获取key,动态为属性赋值.
    @Value("${mysql.username}")
    String username;     // = "root|";
    @Value("${mysql.password}")
    String password;     // = "root";

    @RequestMapping("/getMsg")
    public String getMsg(){

        return "你好数据库:"+ username +password;
    }

    /**
     * 难点: 如何将pro文件交给Spring容器管理????
     * 解决方案: @PropertySource("xxxxxx/xxx.properties") 指定配置文件交给Spring
     *          容器管理
     */
    @Value("${mysql.username2}")
    private String username2;
    @Value("${mysql.password2}")
    private String password2;

    @RequestMapping("/getMsg2")
    public String getMsg2(){

        return "你好数据库:"+ username2 +password2;
    }
}
