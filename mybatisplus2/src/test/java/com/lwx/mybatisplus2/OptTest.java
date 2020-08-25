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

@RunWith(SpringRunner.class)
@SpringBootTest
public class OptTest {
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
        int version = 1;
        User user = new User();
        user.setId(1297197158746890241l);
        user.setEmail("965932944@qq.com");
        user.setVersion(1);
        int rows = userMapper.updateById(user);
        System.out.println(rows);
    }

    @Test
    public void mySelete() {
        List<User> users = userMapper.mySelectList(Wrappers.<User>lambdaQuery().gt(User::getAge, 25));
        users.forEach(System.out::println);
    }
}
