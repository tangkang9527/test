package com.jt;

import com.jt.mapper.DemoUserMapper;
import com.jt.pojo.DemoUser;
import org.apache.catalina.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMybatis {

    /**
     * 业务说明: 实现mybatis入门案例
     * 步骤:
     *      1.动态生成SqlSessionFactory
     *
     */
    @Test
    public void demo1() throws IOException {
        //指定配置文件地址
        String resource = "mybatis/mybatis-config.xml";
        //通过IO流 加载指定的配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //动态生成SqlSessionFactory
        SqlSessionFactory sqlSessionFactory =
                                new SqlSessionFactoryBuilder().build(inputStream);
        //获取SqlSession  类比 数据库链接
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper接口
        DemoUserMapper demoUserMapper = sqlSession.getMapper(DemoUserMapper.class);
        //获取数据
        List<DemoUser> userList = demoUserMapper.findAll();
        System.out.println(userList);
        //关闭链接
        sqlSession.close();
    }
}
