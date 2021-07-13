package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  //自动实现getter setter
@AllArgsConstructor  //全参数构造函数
@NoArgsConstructor  //无参构造函数
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T      data;

    public CommonResult(Integer code,String message){
        this(code,message,null);
    }
}
