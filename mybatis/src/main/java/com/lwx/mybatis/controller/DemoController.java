package com.lwx.mybatis.controller;

import com.lwx.mybatis.domain.User;
import com.lwx.mybatis.enums.UserSexEnum;
import com.lwx.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Resource
    private UserMapper userMapper;

    @GetMapping("/insert")
    @ResponseBody
    public void insert() {
        userMapper.insert(new User("aa", "a123456", UserSexEnum.MAN));
        userMapper.insert(new User("bb", "b123456", UserSexEnum.WOMAN));
        userMapper.insert(new User("cc", "c123456", UserSexEnum.WOMAN));
    }

    @GetMapping("/getAll")
    @ResponseBody
    public List<User> getAll() {
        return userMapper.getAll();
    }

    @GetMapping("/updateById")
    @ResponseBody
    public User updateById(@RequestParam("id") Long  id) {
        User user = userMapper.getOne(id);
        if (Optional.ofNullable(user).isPresent()) {
            user.setNickName(String.valueOf(System.currentTimeMillis()));
            userMapper.update(user);
        }
        return user;
    }

    @GetMapping("/getjoinAll")
    @ResponseBody
    public List<User> getjoinAll() {
        return userMapper.getjoinAll();
    }

    @GetMapping("/getjoinAllT")
    @ResponseBody
    public List<User> getjoinAllT() {
        return userMapper.getjoinAllT();
    }
}
