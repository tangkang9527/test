package com.jt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Emp implements Serializable {
    private Integer id;     //3
    private String name;    //八戒
    private Integer age;    //99
    //一对一需要在代码层级进行表示  使用对象封装
    //private Integer deptId;
    private Dept dept;  //一对一
}
