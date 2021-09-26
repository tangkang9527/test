package com.jt;

import com.jt.mapper.DemoUserMapper;
import com.jt.pojo.DemoUser;
import org.apache.catalina.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMybatis {

    //定义公共的属性
    private SqlSessionFactory sqlSessionFactory;

    @BeforeEach
    public void init() throws IOException {
        //1.指定资源文件
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testMybatis01(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DemoUserMapper demoUserMapper = sqlSession.getMapper(DemoUserMapper.class);
        List<DemoUser> list = demoUserMapper.findAll();
        System.out.println(list);
        sqlSession.close();
    }

    /*
    *   封装DemoUser的对象,根据对象中不为null的属性查询
    * */
    @Test
    public void testFindWhere(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DemoUserMapper demoUserMapper = sqlSession.getMapper(DemoUserMapper.class);
        DemoUser demoUser = new DemoUser();
        demoUser.setAge(3000);
        List<DemoUser> list = demoUserMapper.findWhere(demoUser);
        System.out.println(list);
        sqlSession.close();
    }


}
