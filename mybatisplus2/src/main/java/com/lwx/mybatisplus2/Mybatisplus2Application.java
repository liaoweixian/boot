package com.lwx.mybatisplus2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.lwx.mybatisplus2.mapper"})
public class Mybatisplus2Application {

    public static void main(String[] args) {
        SpringApplication.run(Mybatisplus2Application.class, args);
    }

}
