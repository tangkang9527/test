package com.jt.controller;

import com.jt.pojo.User;
import com.jt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    //http://localhost:8090/findUserByIds?id=1,3,5,6,7
    @GetMapping("/findUserByIds")
    public String findUserByIds(HttpServletRequest request){
        String id = request.getParameter("id");
        //拆分数组
        String[] idStr = id.split(",");
        Integer[] intArray = new Integer[idStr.length];
        //数组转化
        for (int i=0;i<idStr.length;i++){
            intArray[i] = Integer.parseInt(idStr[i]);
        }
        System.out.println(intArray);
        return "参数接收成功!!!";
    }

    /**
     * Servlet参数传递核心规则
     * http://localhost:8090/findServlet?name="张三"
     * 问题: String name 值从哪里来????
     * 核心: 参数是取的  而不是传的
     * 请求的流程: 一个request对象,返回response
     * 注意事项:
     *   1.参数名称必须相同.
     *   2.弊端无论什么样的数据,都是String数据类型,需要手动的转化
     * SpringMVC:
     *   在内部封装了Servlet机制.并且可以根据用户的参数类型,实现自动的数据
     *   类型的转化
     */
    @GetMapping("/findServlet")
    public String findServlet(Integer age){

        return "获取数据:"+age;
    }

    /*@GetMapping("/findServlet")
    public String findServlet(HttpServletRequest request){
        String age = request.getParameter("age");
        Integer ageInt = Integer.parseInt(age);
        return "获取数据:"+age;
    }*/

}
