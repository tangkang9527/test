package com.jt;

import com.jt.mapper.DemoUserMapper;
import com.jt.mapper.DeptMapper;
import com.jt.mapper.EmpMapper;
import com.jt.pojo.DemoUser;
import com.jt.pojo.Dept;
import com.jt.pojo.Emp;
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

    @Test
    public void testFindDept(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
        List<Dept> list = deptMapper.findAll();
        System.out.println(list);
        sqlSession.close();
    }

    /**
     * 完成一对一映射.
     * 规定:  一个员工对应一个部门.
     * 选取方向: 员工方
     * 需求: 需要在员工中 完成部门对象的封装.
     */
    @Test
    public void testOneToOne(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> list = empMapper.findAll();
        System.out.println(list);
        sqlSession.close();
    }

    /*一对一查询 方式2 where 条件子查询 */
    @Test
    public void testOneToOne2(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> list = empMapper.findAllWhere();
        System.out.println(list);
        sqlSession.close();
    }

    /**
     * 一对多的测试  一个部门下有多个用户信息
     */
    @Test
    public void testOneToMore(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
        List<Dept> list = deptMapper.findDept();
        System.out.println(list);
        sqlSession.close();
    }

    /**
     * Mybatis一级缓存: 默认开启
     *      规则: 同一个SqlSession内部有效.
     */
    @Test
    public void cache1(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DemoUserMapper demoUserMapper = sqlSession.getMapper(DemoUserMapper.class);
        List<DemoUser> list1 = demoUserMapper.findAll();
        List<DemoUser> list2 = demoUserMapper.findAll();
        List<DemoUser> list3 = demoUserMapper.findAll();
        System.out.println(list1 == list2);
        sqlSession.close();
    }

    /**
     * 二级缓存说明:
     *     sqlSession查询数据之后,会将缓存信息保存到一级缓存中.但是不会立即将
     *     缓存交给二级缓存保管.
     *     如果需要使用二级缓存,则必须将sqlSession业务逻辑执行成功
     *     之后,并且关闭.
     */
    @Test
    public void cache2(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DemoUserMapper demoUserMapper = sqlSession.getMapper(DemoUserMapper.class);
        demoUserMapper.findAll();
        //关闭一级缓存
        sqlSession.close();

        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        DemoUserMapper demoUserMapper2 = sqlSession2.getMapper(DemoUserMapper.class);
        demoUserMapper2.findAll();
        sqlSession2.close();
    }

}
