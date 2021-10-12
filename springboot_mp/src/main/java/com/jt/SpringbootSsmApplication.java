package com.jt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//根据包扫描路径 扫描全部的mapper接口文件
@MapperScan("com.jt.mapper")
public class SpringbootSsmApplication {
    //创建springboot_ssm2 独立完成项目整合
    public static void main(String[] args) {
        SpringApplication.run(SpringbootSsmApplication.class, args);
    }
}
