package com.jt.mapper;

import com.jt.pojo.User;
import org.apache.ibatis.annotations.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import java.util.List;
public interface UserMapper {
    List<User> findAll();

}
