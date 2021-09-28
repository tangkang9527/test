package com.jt.mapper;

import com.jt.pojo.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptMapper {

    List<Dept> findAll();

    List<Dept> findDept();

    Dept findDeptById(int id);
}
