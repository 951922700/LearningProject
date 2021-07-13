package com.lyl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {
    @GetMapping("/hello")
    public Map<String,Object> hello(){
        Map<String,Object> map=new HashMap<>();
        map.put("userName","gkd");
        return map;
    }
}
