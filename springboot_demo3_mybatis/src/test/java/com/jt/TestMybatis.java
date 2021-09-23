package com.jt;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class TestMybatis {

    /**
     * 业务说明: 实现mybatis入门案例
     * 步骤:
     *      1.动态生成SqlSessionFactory
     *
     */
    public void demo1() throws IOException {
        //指定配置文件地址
        String resource = "mybatis/mybatis-config.xml";
        //通过IO流 加载指定的配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //动态生成SqlSessionFactory
        SqlSessionFactory sqlSessionFactory =
                                new SqlSessionFactoryBuilder().build(inputStream);
    }
}
