package com.lwx.mybatisplus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lwx.mybatisplus.domain.User;
import com.lwx.mybatisplus.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/sample")
public class SampleController {

    private UserMapper userMapper;

    @GetMapping("/select")
    @ResponseBody
    private List<User> select() {
        System.out.println(userMapper);
        List<User> users = userMapper.selectList(null);
        return users;
    }
}
