package com.jt.mapper;


import com.jt.pojo.DemoUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 说明:
 *      1.根据面向接口开发的思想需要定义一个Mapper接口
 *      2.在接口中可以写接口方法, 谁用谁去实现!!!
 *      3.Mybatis中的实现类以xml文件的形式存在
 */
public interface DemoUserMapper {

    //1.查询所有的表数据
    public List<DemoUser> findAll();

    DemoUser findOne(int id);

    List<DemoUser> findByName(String name);

    List<DemoUser> findBySA(DemoUser user);
    //利用@Param注解 将参数封装为Map集合
    List<DemoUser> findBySA2(@Param("sex") String sex, @Param("age") int age);

    List<DemoUser> findBySA3(Map<String, Object> map);
}
