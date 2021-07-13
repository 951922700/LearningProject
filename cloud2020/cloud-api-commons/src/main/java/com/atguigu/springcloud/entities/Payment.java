package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//整合lombok
@Data  //自动实现getter setter
@AllArgsConstructor  //全参数构造函数
@NoArgsConstructor  //无参构造函数
public class Payment {
    private Long id;
    private String serial;
}
