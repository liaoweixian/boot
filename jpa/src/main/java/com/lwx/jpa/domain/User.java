package com.lwx.jpa.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Table(name = "user")
@Entity
@Getter
@Setter
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // unique = true 唯一标识
    @Column(unique = true)
    private String username;

    private String nickName;

    private String sex;

    private Long avatar_id;

    private String email;

    private String phone;

    private Boolean enabled;

    private String password;

    private Timestamp createTime;

    private Date lastPasswordResetTime;

    private Long jobId;

    private Long deptId;




}
