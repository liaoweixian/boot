package com.lwx.mybatisplus2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwx.mybatisplus2.domain.User;
import com.lwx.mybatisplus2.mapper.UserMapper;
import com.lwx.mybatisplus2.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
