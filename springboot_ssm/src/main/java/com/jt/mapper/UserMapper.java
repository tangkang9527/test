package com.jt.mapper;

import com.jt.pojo.User;
import org.apache.ibatis.annotations.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import java.util.List;
//将该接口交给Spring容器管理
//@Mapper
public interface UserMapper {
    //查询demo_user表中的所有数据
    List<User> findAll();
    //根据Id查询数据
    User findUserById(Integer id);
    //@Insert("sql语句")
    //@Select("查询操作的sql")
    @Update("update demo_user set name=#{name}, age=#{age} where id=#{id}")
    //@Delete("xxxxx")
    void update(User user);
    @Select("select * from demo_user where age=#{age} and sex=#{sex}")
    List<User> findUserByAS(User user);

    List<User> findUserByNA(User user);

    List<User> findUserByLike(String name);

    //为了操作方便将形参改名为ids
    List<User> findUserByIds(Integer[] ids);

    List<User> findUser(User user);

    @Select("select * from demo_user where name=#{name} and sex=#{sex}")
    List<User> findUserByNS(User user);
    @Insert("insert into demo_user value(null,#{name},#{age},#{sex})")
    void saveUser(User user);

    @Update("update demo_user set name=#{name},age=#{age},sex=#{sex} where id=#{id}")
    void updateUser(User user);
}
