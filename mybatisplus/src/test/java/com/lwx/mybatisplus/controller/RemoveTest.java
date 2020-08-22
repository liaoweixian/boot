package com.lwx.mybatisplus.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lwx.mybatisplus.domain.User;
import com.lwx.mybatisplus.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RemoveTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void deleteById() {
        int rows = userMapper.deleteById(1296462611029811202l);
        System.out.println(rows);
    }

    @Test
    public void deleteByWrapper() {
        User whereUser = new User();
        whereUser.setName("向西");
        LambdaUpdateWrapper<User> wrapper = Wrappers.lambdaUpdate(whereUser);
//        wrapper.eq(User::getName, "向后");
        int rows = userMapper.delete(wrapper);
        System.out.println(rows);
    }
}
