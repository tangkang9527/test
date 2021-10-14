package com.jt.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.jt.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
               //什么是bean spring容器管理的对象叫做bean
@Configuration //标识这是一个配置类 相当于早期的xml文件
public class MybatisPlusConfig {

   /**
    *  JS-钩子函数-生命周期函数!!!!!!!!
    *  MP生命周期方法:  itemMapper.selectPage--自动调用MybatisPlusInterceptor对象
    *  @Bean作用: 将方法的返回值交给Spring容器管理
    * @return
    */
   @Bean
   public MybatisPlusInterceptor mybatisPlusInterceptor() {
       MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
       interceptor.addInnerInterceptor
               (new PaginationInnerInterceptor(DbType.MARIADB));
       return interceptor;
   }


  /* @Bean
   public User user(){
       User user = new User();
       user.setId(10).setUsername("xxxxx");
       return user;
   }*/
}
