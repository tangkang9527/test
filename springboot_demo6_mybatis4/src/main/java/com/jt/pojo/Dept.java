package com.jt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Dept implements Serializable{
    //驼峰命名规则
    private Integer deptId;
    private String deptName;
    //关联关系: 一个部门对应多个员工
    private List<Emp> emps;
}
