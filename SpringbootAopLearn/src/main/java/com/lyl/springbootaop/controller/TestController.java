package com.lyl.springbootaop.controller;

import com.lyl.springbootaop.service.UserService;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    UserService userService;

    //todo 几种参数注解
    @GetMapping("/test")
    public String test(Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping("/add")
    public void add() {
         userService.add();
    }
}
