package com.lwx.mybatisplus.controller;

import com.lwx.mybatisplus.domain.User;
import com.lwx.mybatisplus.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void select() {
        List<User> users = userMapper.selectList(null);
        Assert.assertEquals(5, users.size());
        users.forEach(System.out::println);
    }

    /**
     * 这里有个地方要注意
     * 1/这里没有插入email字断
     * Preparing: INSERT INTO user ( id, name, age, manager_id, create_time ) VALUES ( ?, ?, ?, ?, ? )
     * 在插入或修改时，mp默认为 字断为null，进行排除
     * mp 对主键id，默认使用雪花设置主键id
     * mp 对字断默认进行驼峰表达式
     */
    @Test
    public void insert() {
        User user = new User();
        user.setName("向化");
        user.setAge(23);
        user.setEmail("965932944@qq.com");
        user.setManagerId(1088248166370832385L);
        user.setCreateTime(LocalDateTime.now());
        user.setRemark("我是一个备注信息");
        int rows = userMapper.insert(user);
        System.out.println(rows);
    }

}
