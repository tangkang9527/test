package com.jt;

import com.jt.mapper.DemoUserMapper;
import com.jt.pojo.DemoUser;
import org.apache.catalina.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

      /**
       * 需求 :2. 查询sex=女 and age > 18岁
       * 方式1: User对象封装
       */
      @Test
      public void testFindBySA(){
            //保证每个线程都能获取一个链接
            SqlSession sqlSession = sqlSessionFactory.openSession();
            DemoUserMapper demoUserMapper = sqlSession.getMapper(DemoUserMapper.class);
            //编程习惯: 面向对象
            DemoUser user = new DemoUser();
            user.setAge(18).setSex("女");
            List<DemoUser> list = demoUserMapper.findBySA(user);
            System.out.println(list);
            sqlSession.close();
      }

      /**
       *    sex=女 and age > 18
       *    方式2: @Param方式封装.
       */

      @Test
      public void testFindBySA2(){
            //保证每个线程都能获取一个链接
            SqlSession sqlSession = sqlSessionFactory.openSession();
            DemoUserMapper demoUserMapper = sqlSession.getMapper(DemoUserMapper.class);
            String sex = "女";
            int age = 18;
            List<DemoUser> list = demoUserMapper.findBySA2(sex,age);
            System.out.println(list);
            sqlSession.close();
      }

      /**
       *    sex=女 and age > 18
       *    方式3: map集合封装
       */

      @Test
      public void testFindBySA3(){
            //保证每个线程都能获取一个链接
            SqlSession sqlSession = sqlSessionFactory.openSession();
            DemoUserMapper demoUserMapper = sqlSession.getMapper(DemoUserMapper.class);
            Map<String,Object> map = new HashMap<>();
            map.put("sex","女");
            map.put("age",18);
            List<DemoUser> list = demoUserMapper.findBySA3(map);
            System.out.println(list);
            sqlSession.close();
      }

      /**
       *  需求: 要求按照指定的age排序
       *  Sql: select * from demo_user order by age
       *  #号和$符用法.
       */
      @Test
      public void testFindOrder(){
            SqlSession sqlSession = sqlSessionFactory.openSession();
            DemoUserMapper demoUserMapper = sqlSession.getMapper(DemoUserMapper.class);
            String column = "age";
            List<DemoUser> list = demoUserMapper.findOrder(column);
            System.out.println(list);
            sqlSession.close();
      }

}
