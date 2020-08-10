package com.lwx.jpa.controller;

import com.lwx.jpa.domain.Dept;
import com.lwx.jpa.repository.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptRepository deptRepository;

    @GetMapping("/getDeptList")
    @ResponseBody
    public List<Dept> getDeptList() {
        return deptRepository.findAll();
    }
}
