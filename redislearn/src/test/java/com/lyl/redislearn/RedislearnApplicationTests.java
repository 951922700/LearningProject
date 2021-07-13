package com.lyl.redislearn;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyl.redislearn.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;


@SpringBootTest
class RedislearnApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("a","c");
        System.out.println(redisTemplate.opsForValue().get("a"));
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        redisTemplate.multi();//开启事务
        redisTemplate.exec();//执行事务
        connection.flushDb();//清空数据库
        connection.close();
    }

    @Test
    public void test() throws JsonProcessingException {
        //如果使用默认jdk序列化 在redis客户端显示会转码
        //自定义序列化一下就可以

        //一般开发用json传递对象
        User  user=new User("aaa",1);
        String s = new ObjectMapper().writeValueAsString(user);//将对象转成json字符串
        System.out.println(s);
        redisTemplate.opsForValue().set("user2",s);//user如果不转成json字符串必须把user序列化才能传输
        System.out.println(redisTemplate.opsForValue().get("user2"));
    }
}
