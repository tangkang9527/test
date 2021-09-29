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
    /*@GetMapping("/findUserByIds")
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
    }*/

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
   /* @GetMapping("/findServlet")
    public String findServlet(Integer age){

        return "获取数据:"+age;
    }*/

    /*@GetMapping("/findServlet")
    public String findServlet(HttpServletRequest request){
        String age = request.getParameter("age");
        Integer ageInt = Integer.parseInt(age);
        return "获取数据:"+age;
    }*/


    /**
     * 作业:
     *  5. 更新操作 利用restFul的结构, 根据Id修改数据,修改name/age
     *     URL:http://localhost:8090/user/貂蝉/18/227  PUT
     *     查询: http://localhost:8090/user/18         GET
     *     解析: URL:http://localhost:8090/user/{name}/{age}/{id}
     *
     *  RestFul语法:
     *      1.用户url编辑
     *          1.1 参数与参数之间使用/分割.
     *          1.2 restFul结构顺序一旦确定,不能随意更改
     *          1.3 如果使用restFul结构,请求路径中不能出现动词.隐藏目的.
     *
     *      2.用户规范:
     *          由于restFul结构请求路径都是名词,所以不能区分业务逻辑.
     *          所以采用请求类型,严格区分业务需求.
     *          2.1  GET   查询操作
     *          2.2  POST  新增操作/form表单提交
     *          2.3  PUT   修改操作
     *          2.4  DELETE 删除操作
     *
     *      3.参数接收
     *          1.参数与参数之间使用/分割.
     *          2.参数必须使用{xxx}包裹
     *          3.使用特定的注解@PathVariable("name") String name 接收参数
     *          4.如果接收参数的名称与对象中的属性名称一致,则可以使用对象接收
     *
     */
    @GetMapping("/user/{name}/{age}/{id}")
    public Object restFul(User user){

        userService.update(user);
        return "数据更新成功!!!";
    }


    /**
     * 需求: 根据name和age查询数据
     * URL: URL: http://localhost:8090/findUserByNA?name=xxx&age=xxx
     * 参数: name/age
     * 返回值: List<User>
     */
    @GetMapping("/findUserByNA")
    public List<User> findUserByNA(User user){

        return userService.findUserBYNA(user);
    }

    /**
     * 需求:查询以xx结尾的数据
     * URL: http://localhost:8090/findUserLike?name=xx
     * 参数: name=xx
     * 返回值: List<User></>
     */
    @GetMapping("/findUserLike")
    public List<User> findUserLike(String name){

        return userService.findUserByLike(name);
    }

    /**
     * 作业3: 根据ID批量查询
     * URL: http://localhost:8090/findUserByIds?id=1,3,5,6,7
     * 参数: id=1,3,5,6,7
     * 返回值: List<User>
     * 功能: 如果多个参数使用,号分割,则默认可以使用数组接收
     */
    @GetMapping("/findUserByIds")
    public List<User> findUserIds(Integer[] id){

        return userService.findUserByIds(id);
    }

    /**
     * 作业4: 动态Sql
     * URL: http://localhost:8090/findUser?id=xx&name="xx"&age=xx&sex=xx
     * 参数: id/age/name/sex 可能有
     * 结果: List<User>
     */
    @GetMapping("/findUser")
    public List<User> findUser(User user){

        return userService.findUser(user);
    }

}
