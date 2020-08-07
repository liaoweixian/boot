package com.lwx.jpa.server;

import com.lwx.jpa.domain.User;
import com.lwx.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServer {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUserList() {
        return userRepository.findAll();
    }

    /**
     * 分页查询
     * @return
     */
    public Page<User> getUserPage(Pageable page) {
        return userRepository.findAll(page);
    }


}
