package com.lwx.mybatisplus2;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lwx.mybatisplus2.domain.User;
import com.lwx.mybatisplus2.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * 由于User 使用了逻辑删除， 在删除user信息时没有真的删除，mp自己进行里逻辑删除
 * 1、select 时，自动在sql后面加逻辑删除字断 只查询未被逻辑删除的数据
 * 2、update 时，自动在sql后面加 逻辑删除字断 只更新未被逻辑删除的数据
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FillTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setName("刘明超");
        user.setAge(31);
        user.setEmail("qqqqq@qq.com");
        user.setManagerId(1088248166370832385L);
        int rows = userMapper.insert(user);
        System.out.println(rows);
    }


    @Test
    public void updateById() {
        User user = new User();
        user.setAge(26);
        user.setId(1088248166370832385L);
        int rows = userMapper.updateById(user);
        System.out.println(rows);
    }

    @Test
    public void mySelete() {
        List<User> users = userMapper.mySelectList(Wrappers.<User>lambdaQuery().gt(User::getAge, 25));
        users.forEach(System.out::println);
    }
}
