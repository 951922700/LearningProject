package com.lyl.comsumeruser;

import com.lyl.comsumeruser.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ComsumerUserApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        userService.hello();
    }

}
