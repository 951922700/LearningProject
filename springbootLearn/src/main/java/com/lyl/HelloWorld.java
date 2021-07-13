package com.lyl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 指明这是一个springboot应用
 */
@SpringBootApplication
public class HelloWorld {
    public static void main(String[] args) {
        //spring应用启动
        SpringApplication.run(HelloWorld.class, args);

    }
}
