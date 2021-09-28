package com.jt.mapper;

import com.jt.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import java.util.List;
//将该接口交给Spring容器管理
//@Mapper
public interface UserMapper {
    //查询demo_user表中的所有数据
    List<User> findAll();
}
