package com.jt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

//效果:只要@Test测试方法执行,则整个Spring容器启动,可以根据自身的需要实现依赖注入
//注意事项:  该注解只能在测试类中使用.
//         测试类的包路径必须在主启动类的同包及子包中编辑.
@SpringBootTest
class SpringbootSsmApplicationTests {

    @Autowired
    private UserMapper userMapper;

    /**
     * MP入门案例
     */
    @Test
    public void insertUser() {
        User user = new User();
        user.setId(null).setName("MybatisPlus")
                        .setAge(10).setSex("男");
        userMapper.insert(user);
    }

    /**
     * 学习技巧: MP设计思想!!!!  对象
     * 查询Id=1的用户
     */
    @Test
    public void selectById() {
        int id = 1;
        User user = userMapper.selectById(id);
        System.out.println(user);
    }

    /**
     * 查询name="大乔",sex="女"的用户
     * 语法:
     *      1.根据对象中不为null的属性进行业务操作
     *      2.QueryWrapper条件构造器 动态拼接where条件
     *      3.默认的关系连接符 and
     * 例子:
     *      select * from demo_user where xx=xx and xx=xx
     */
    @Test
    public void selectByNS() {
       User user = new User();
       user.setName("大乔").setSex("女");
       QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
       List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }

    /**
     * 查询name="大乔",sex="女"的用户
     * 方式2: 利用条件构造器,构建条件
     * 说明:
     *     1. eq =    2. gt >
     *     3. lt <    4. ge >=
     *     5. le <=   6. ne <>
     */
    @Test
    public void selectByNS2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name","大乔")
                    .eq("sex","女");
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }

    /**
     * 需求: 查询age>18岁的用户,并且sex=男.
     */
    @Test
    public void selectByAS() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age",18)
                    .eq("sex","男");
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }

    /**
     * 需求: 查询name中包含"君",性别="女"
     * Sql:  where name like "%君%"
     * 需求2: 查询name中以"君"结尾的,性别="女"  like "%君"
     * 语法说明:  likeLeft %  左侧的数据
     */
    @Test
    public void selectLike() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //queryWrapper.like("name","君")   //"%君%"
        queryWrapper.likeLeft("name","君") //"%君"
                    .eq("sex","女");
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }

    /**
     * 需求: 查询id=1,3,4,5的数据 并且按照年龄降序排列
     * 规则: 基本类型有没有方法? 所以使用包装类型
     *      面向对象开发
     */
    @Test
    public void selectIds() {
        Integer[] ids = {1,3,4,5};
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id",ids)
                    .orderByDesc("age");
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }

    /**
     * 需求:
     *  只想获取第一列数据(主键),sex="女"
     * 用法: .selectObjs(queryWrapper);
     * 实际用途: 做关联查询时可以使用
     */
    @Test
    public void selectObjs() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sex","女");
        List list = userMapper.selectObjs(queryWrapper);
        System.out.println(list);
    }
}
