package com.example;

import com.example.service.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SpringbootShiroApplicationTests {
    @Resource(name = "userService")
    UsersService usersService;
    @Test
    void contextLoads() {
        System.out.println(usersService.getUsersByName("czy").toString());
    }

}
