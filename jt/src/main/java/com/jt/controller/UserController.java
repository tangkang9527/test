package com.jt.controller;

import com.jt.pojo.User;
import com.jt.service.UserService;
import com.jt.vo.PageResult;
import com.jt.vo.SysResult;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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

    /**
     * 需求: 利用分页查询用户信息
     * URL:  /user/list
     * 参数: http://localhost:8091/user/list?query=查询关键字&pageNum=1&pageSize=10
     * 返回值: SysResult(pageResult)
     */
    @GetMapping("/list")
    public SysResult getUserListByPage(PageResult pageResult){

        pageResult = userService.getUserListByPage(pageResult);
        return SysResult.success(pageResult);
    }

    /**
     * 需求: 根据Id修改状态
     * URL:  /user/status/{id}/{status}  restFul
     * 参数:  id/status
     * 返回值: SysResult对象
     */
    @PutMapping("/status/{id}/{status}")
    public SysResult updateStatusById(User user){

        userService.updateStatusById(user);
        return SysResult.success();
    }

    /**
     * 用户新增
     *  URL:  /user/addUser
     *  参数:  JS对象经过浏览器解析变为JSON串
     *        {"username":"111","password":"222","password2":"222","email":"22@qq.com","phone":"13111111111"}
     *  返回值: SysResult对象
     *      对象转化为JSON @ResponseBody
     *      JSON转化为对象 @RequestBody  规则
     */
    @PostMapping("/addUser")
    public SysResult saveUser(@RequestBody User user){

        userService.saveUser(user);
        return SysResult.success();
    }

    /**
     * 业务: 根据ID查询用户数据
     * URL: /user/{id}
     * 参数: id
     * 返回值: SysResult对象
     */
    @GetMapping("/{id}")
    public SysResult findUserById(@PathVariable Integer id){
        User user = userService.findUserById(id);
        return SysResult.success(user);
    }


    /**
     * 用户修改操作
     * URL:  /user/updateUser
     * 参数:  User(id/phone/email) JSON串
     * 返回值: SysResult对象
     */
    @PutMapping("/updateUser")
    public SysResult updateUser(@RequestBody User user){

        userService.updateUser(user);
        return SysResult.success();
    }

    /**
     * 删除数据
     * URL: /user/{id}
     * 参数: id
     * 返回值: SysResult对象
     */
    @DeleteMapping("/{id}")
    public SysResult deleteById(@PathVariable Integer id){
        //防止与MP方法冲突 业务方法最好添加业务名称
        userService.deleteUserById(id);
        return SysResult.success();
    }
}
