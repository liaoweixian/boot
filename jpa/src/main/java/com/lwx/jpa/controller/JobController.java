package com.lwx.jpa.controller;

import com.lwx.jpa.domain.Job;
import com.lwx.jpa.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobRepository jobRepository;

    @GetMapping("/getJobList")
    @ResponseBody
    public List<Job> getJobList() {
        return jobRepository.findAll();
    }
}
