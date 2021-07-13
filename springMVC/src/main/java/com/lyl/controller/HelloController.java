package com.lyl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 控制器：接收请求
 * @ RequestMapping注解属性 path指定请求路径 method指定请求方式
 * params 用于指定限制请求参数的条件，他支持简单的表达式，要求请求参数的key和value必须和配置的一模一样
 * params = {"username"}必须传username这个参数"username=heihei"参数的key和value必须一样
 * headers ={"Accept"}限制请求头消息
 */
@Controller
@RequestMapping(path = "/user",method ={RequestMethod.POST,RequestMethod.GET})
public class HelloController {
    @RequestMapping(path = "/hello")
    public String sayHello(){
        System.out.println("hello springmvc!");
        return "success";
    }
}
