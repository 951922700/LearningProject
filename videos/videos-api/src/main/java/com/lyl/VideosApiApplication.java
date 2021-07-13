package com.lyl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = {"com.lyl", "org.n3r.idworker"})
@MapperScan(basePackages = "com.lyl.mapper")
//@ComponentScan(basePackages= {"com.lyl"})
public class VideosApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(VideosApiApplication.class, args);
    }


}
