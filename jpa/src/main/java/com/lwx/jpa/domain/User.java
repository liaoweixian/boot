package com.lwx.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

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

    // private Long deptId;

    /**
     * @JsonIgnoreProperties(value = "user") 用于阻止双向关联，序列化问题导致死循环，
     * 用于阻止死循环 value 指定的是关联对象里面的 User对象（也就是自己）清除掉 或者阻止序列化
     *
     * 可以重写toString 直接清除掉 但前对象包含的关联对象中的自己
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_id")
    @JsonIgnoreProperties(value = "user")
    private Job job;

    @OneToOne
    @JoinColumn(name = "dept_id", referencedColumnName = "id")
    private Dept dept;

    /**
     * 1、joinColumns 代表关联当前类，带注解的类
     * 2、inverseJoinColumns 代表被关联的类
     * 3、这里的 @JoinColumn的name 指定的是 user_roles 的字段 中间表的字段 referencedColumnName 代表是关联表的字段
     * 4、@ManyToMany的 fetch 指的是加载策略
     *  -- FetchType.EAGER及时加载
     *  -- FetchType.LAZY 懒加载，使用时加载
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "id")
    })
    private Set<Role> roles;


}
