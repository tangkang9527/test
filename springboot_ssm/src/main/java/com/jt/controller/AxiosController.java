package com.jt.controller;

import com.jt.pojo.User;
import com.jt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 业务:实现用户的新增
     * URL: http://localhost:8090/axios/saveUser
     * 参数: {"name":"国庆快乐","age":72,"sex":"女"} JSON串
     * 返回值: String 新增成功
     * 知识点: post请求传递对象时,传递的数据是一个json串.不能直接为User对象赋值.
     * 解决方案:
     *        1.对象转化为JSON  @ResponseBody
     *        2.JSON转化为对象  @RequestBody
     *
     */
    @PostMapping("/saveUser")
    public String saveUser(@RequestBody User user){
        System.out.println(user);
        userService.saveUser(user);
        return "用户入库成功!!!";
    }


    /**
     * 需求:获取user全部列表
     * URL: /axios/findAll
     * 参数: 没有
     * 返回值: List<User>
     */
    @GetMapping("/findAll")
    public List<User> findAll(){

        return userService.findAll();
    }

    /**
     * 业务: 实现用户更新
     * URL: /axios/updateUser
     * 参数: json串
     * 返回值: 提示信息
     */
    @PutMapping("updateUser")
    public String updateUser(@RequestBody User user){

        userService.updateUser(user);
        return "更新操作成功!";
    }
}
