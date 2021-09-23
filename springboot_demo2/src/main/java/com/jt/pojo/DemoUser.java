package com.jt.pojo;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 实体对象要求:
 *      1.类名一般与表名关联
 *      2.属性名称一般与字段关联
 *      3.pojo中的属性类型必须为引用类型(包装类型)
 *      4.实体对象必须有get/set方法
 *      5.一般实体对象需要实现序列化接口(规则)
 *          原因: 数据可能跨平台(跨服务器)传输,必须序列化
 */
public class DemoUser implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
    private String sex;

}
