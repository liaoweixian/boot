package com.lwx.jpa.controller;

import com.lwx.jpa.domain.Job;
import com.lwx.jpa.domain.User;
import com.lwx.jpa.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServer userServer;

    @GetMapping("/getUserList")
    @ResponseBody
    public List<User> getUserList() {
        return userServer.getUserList();
    }

    @GetMapping("/getUserPage")
    @ResponseBody
    public Page<User> getUserPage() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest page = PageRequest.of(0, 10, sort);
        return userServer.getUserPage(page);
    }

    @GetMapping("/getUserListSpecification")
    @ResponseBody
    public List<User> getUserListSpecification() {
        User user = new User();
        user.setNickName("管理员");
        user.setEmail("zhengjie%");
        Job job = new Job();
        job.setName("人事专员");
        user.setJob(job);
        return userServer.getUserListSpecification(user);
    }
}
