package com.lwx.jpa.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class Demo {

    @GetMapping(value = "/hello")
    @ResponseBody
    public String hello() {
        return "hello world";
    }
}
