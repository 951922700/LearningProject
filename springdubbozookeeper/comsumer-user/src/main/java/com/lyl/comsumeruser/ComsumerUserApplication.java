package com.lyl.comsumeruser;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TicketService的全路径名字一定要相同
 */
@EnableDubbo
@SpringBootApplication
public class ComsumerUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComsumerUserApplication.class, args);
    }

}
