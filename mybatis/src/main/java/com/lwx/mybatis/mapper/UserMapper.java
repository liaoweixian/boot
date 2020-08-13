package com.lwx.mybatis.mapper;

import com.lwx.mybatis.domain.Nature;
import com.lwx.mybatis.domain.User;

import java.util.List;

public interface UserMapper {

    List<User> getAll();

    User getOne(Long id);

    void insert(User user);

    void update(User user);

    void delete(Long id);

    List<User> getjoinAll();

    List<User> getjoinAllT();

    Nature getNature(Long id);

}
