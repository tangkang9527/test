package com.jt;

import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}
