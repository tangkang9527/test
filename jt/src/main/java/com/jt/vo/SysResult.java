package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SysResult {

    private Integer status; //200正常 201失败
    private String msg;     //服务器返回提示信息
    private Object data;    //服务器返回业务数据

    /**
     * 说明: SysResult对象是系统的返回值对象,调用的次数较多.
     * 如果每次都需要手动添加200/201 则较为繁琐,能否封装一些方法
     * 简化代码调用????
     * 解决方案: 添加静态方法简化调用!!!
     */
    public static SysResult fail(){
        return new SysResult(201,"服务器调用失败!",null);
    }

    public static SysResult success(){

        return new SysResult(200,"服务器调用成功!",null);
    }

    //重载规则:参数不要耦合,否则会产生歧义.
    public static SysResult success(Object data){

        return new SysResult(200,"服务器调用成功!",data);
    }

    public static SysResult success(String msg,Object data){

        return new SysResult(200,msg,data);
    }
}
