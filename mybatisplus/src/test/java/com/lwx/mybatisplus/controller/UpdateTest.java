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
public class UpdateTest {

    @Resource
    private UserMapper userMapper;

    /**
     * 以id为条件，进行数据修改
     * 默认 为null的参数 被忽略掉
     */
    @Test
    public void updateById() {
        User user = new User();
        user.setId(1088248166370832385l);
        user.setAge(26);
        user.setEmail(null);
        int rows = userMapper.updateById(user);
        System.out.println(rows);
    }

    /**
     * 条件 update
     */
    @Test
    public void updateByWrapper() {
        User whereUser = new User();
        whereUser.setName("李艺伟");
        whereUser.setAge(29);
        LambdaUpdateWrapper<User> updateWrapper = Wrappers.lambdaUpdate(whereUser);
//        updateWrapper.eq(User::getName, "李艺伟")
//                .eq(User::getAge, 28);
        User user = new User();
        user.setEmail("baomidou@qq.com");
        user.setAge(30);
        int rows = userMapper.update(user, updateWrapper);
        System.out.println(rows);
    }
}
