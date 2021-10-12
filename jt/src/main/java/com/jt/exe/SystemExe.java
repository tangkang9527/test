package com.jt.exe;

import com.jt.vo.SysResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

//1.标识该类是全局异常处理机制.返回值都是JSON串
//  通知: AOP中的技术,解决特定问题的
//  特点: 该异常处理机制,只拦截Controller层抛出的异常
@RestControllerAdvice
public class SystemExe {
    /**
     * 说明:  需要为全局异常定义一个方法.
     * 要求:  返回的统一的业务数据 SysResult
     * 拦截:  指定遇到某种异常实现AOP处理.
     * 注意事项: 在业务方法中不要随意添加try-catch
     */
    @ExceptionHandler({RuntimeException.class})
    public SysResult fail(Exception e){
        e.printStackTrace();
        return SysResult.fail();
    }

}
