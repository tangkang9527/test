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

    /**
     * 需求: 查询age> 18 and age< 100 的用户信息.
     * 规则: 如果不能使用对象封装,则一般使用Map集合
     */
    @Test
    public void testSelect01(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DemoUserMapper demoUserMapper = sqlSession.getMapper(DemoUserMapper.class);
        Map<String,Object> map = new HashMap<>();
        map.put("minAge",18);
        map.put("maxAge",100);
        List<DemoUser> userList = demoUserMapper.findByAge(map);
        System.out.println(userList);
        sqlSession.close();
    }

    /**
     * 例如: 删除id=232/233/234的数据?
     *  Sql: delete from demo_user where id in (232,233,234)
     * 规则: 如果遇到相同的多个数据,则一般采用集合的方式封装数据.
     * 封装方式:
     *      1. array
     *      2. list
     *      3. map<List>
     */
    @Test
    public void testDeleteIds(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        DemoUserMapper demoUserMapper = sqlSession.getMapper(DemoUserMapper.class);
        //将数据封装为数组
        int[] ids = {232,233,234};
        demoUserMapper.deleteIds(ids);
        System.out.println("删除操作成功!!!");
    }

    @Test
    public void testDeleteList(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        DemoUserMapper demoUserMapper = sqlSession.getMapper(DemoUserMapper.class);
        List list = new ArrayList();
        list.add(232);
        list.add(233);
        list.add(234);
        demoUserMapper.deleteList(list);
        System.out.println("删除操作成功!!!");
    }

    /*
    * 说明: 有时业务需求导致需要使用map封装list集合
    */
    @Test
    public void testDeleteMap(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        DemoUserMapper demoUserMapper = sqlSession.getMapper(DemoUserMapper.class);
        List list = new ArrayList();
        list.add(232);
        list.add(233);
        list.add(234);
        HashMap map = new HashMap();
        map.put("ids",list);
        demoUserMapper.deleteMap(map);
        System.out.println("删除操作成功!!!");
    }

    /**
     * 作业:
     *  1.整理bug集,收集Mybatis中常见报错,并且持续维护 20%
     *  2.mybatis案例练习
     *    2.1 查询age<100岁的女性用户.
     *    2.2 查询name中包含"精"的数据.并且按照年龄降序排列
     *    2.3 查询age位于100-300的数据
     *    2.4 将name为小乔/大乔/王昭君的年龄改为18岁,性别女
     *
     */

    /*
        Mybatis作业:
            需求: 查询name中包含"精"的数据.并且按照年龄降序排列
     */
    @Test
    public void findLike(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        DemoUserMapper demoUserMapper = sqlSession.getMapper(DemoUserMapper.class);
        //String name = "精";
        String name = "%精%";
        List<DemoUser> list = demoUserMapper.findLike(name);
        System.out.println(list);
        sqlSession.close();
    }

    /**
     * 作业2: 将name为小乔/大乔/王昭君的年龄改为18岁,性别女
     */
    @Test
    public void updateUser(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        DemoUserMapper demoUserMapper = sqlSession.getMapper(DemoUserMapper.class);
        Map<String,Object> map = new HashMap<>();
        String[] array = {"小乔","大乔","王昭君"};
        map.put("names",array);
        map.put("age",18);
        map.put("sex","女");
        demoUserMapper.updateUser(map);
        sqlSession.close();
    }















}
