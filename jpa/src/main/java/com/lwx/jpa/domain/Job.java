package com.lwx.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Table(name = "job")
@Entity
@Getter
@Setter
public class Job implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id ;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "sort", nullable = false)
    private Long sort;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @CreationTimestamp
    private Timestamp createTime;

    /**
     * 一个职位对应多个 用户 一对多
     */
    @OneToMany(mappedBy = "job")
    @JsonIgnoreProperties(value = "job")
    private List<User> user;
}
