package com.lwx.mybatisplus.controller;

import com.lwx.mybatisplus.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ARTest {

    @Test
    public void insert() {
        User user = new User();
        user.setName("向化");
        user.setAge(23);
        user.setEmail("965932944@qq.com");
        user.setManagerId(1088248166370832385L);
        user.setCreateTime(LocalDateTime.now());
        user.setRemark("我是一个备注信息");
        boolean bool = user.insertOrUpdate();
        System.out.println(bool);
    }
}
