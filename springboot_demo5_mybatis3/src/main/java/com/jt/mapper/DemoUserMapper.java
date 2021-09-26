package com.jt.mapper;


import com.jt.pojo.DemoUser;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DemoUserMapper {

    List<DemoUser> findAll();

}
