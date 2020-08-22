package com.lwx.mybatisplus2.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("user")
public class User implements Serializable {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private Long managerId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer version;
    private Integer deleted;
}
