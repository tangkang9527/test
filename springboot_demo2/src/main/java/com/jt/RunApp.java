package com.jt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RunApp {
    //args是jvm进行的参数的传递.java默认值
    public static void main(String[] args) {
        SpringApplication.run(RunApp.class,args);
    }
}
