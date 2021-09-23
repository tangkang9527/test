package com.jt.mapper;


import com.jt.pojo.DemoUser;

import java.util.List;

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
}
