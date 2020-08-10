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
    /**
     * 用于标注主键的生成策略
     * 组件的增长方式：
     * IDENTITY 主键自增长 采用数据库ID自增长的方式来自增主键字断 Oracle不支持这种方式
     * AUTO JPA自动选择合适的策略，是默认选秀
     * SEQUENCE 通过序列化产生主键，通过@SequenceGenerator 注解指定序列名，MySql不支持这种方式
     * TABLE 通过表产生主键，
     */
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

    // private Long jobId;

    private Long deptId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_id")
    private Job job;


}
