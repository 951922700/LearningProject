package com.lyl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 1.快速体验缓存
 *      开启基于注解的缓存 @EnableCaching
 *      标注缓存注解
 *      @Cacheable
 *      @CacheEvict
 *      @CachePut
 */
@EnableScheduling
@EnableAsync
//@EnableRabbit  //开启基于注解rabbit
@MapperScan(value="com.lyl.mapper") //自动扫眉这个路径下的mapper就不用加mapper注解了
@EnableCaching
@SpringBootApplication
public class SpringBootLearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootLearnApplication.class, args);
    }

}
