package com.jt;

import com.jt.mapper.DemoUserMapper;
import com.jt.pojo.DemoUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
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

}
