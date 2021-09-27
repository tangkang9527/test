package com.jt.mapper;

import com.jt.pojo.Dept;

import java.util.List;

public interface DeptMapper {

    List<Dept> findAll();

    List<Dept> findDept();
}
