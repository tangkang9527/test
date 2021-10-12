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
}
