package com.jt;

import com.jt.mapper.DemoUserMapper;
import com.jt.pojo.DemoUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMybatis2 {

      //定义公共的属性
      private SqlSessionFactory sqlSessionFactory;

      /**
       * mybatis的核心 SqlSessionFacotry对象
       * @BeforeEach: 测试API中的注解 在执行@Test注解方法时,会提前执行!!!
       */
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

      /**
       * 作业:
       *      1. 查询name="王昭君"的用户
       *      2. 查询sex=女 and age > 18岁
       */

      @Test
      public void testFindByName(){
            //保证每个线程都能获取一个链接
            SqlSession sqlSession = sqlSessionFactory.openSession();
            DemoUserMapper demoUserMapper = sqlSession.getMapper(DemoUserMapper.class);
            String name = "王昭君";
            //如果不能保证结果唯一,则使用List集合接收数据.
            List<DemoUser> list = demoUserMapper.findByName(name);
            System.out.println(list);
            sqlSession.close();
      }

}
