package com.lwx.mybatisplus.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lwx.mybatisplus.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void getOne() {
        userService.getOne(Wrappers.lambdaQuery());
    }
}
