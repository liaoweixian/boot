package com.lwx.jpa.domain;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "dept")
@Entity
@Getter
@Setter
public class Dept implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Boolean enabled;

    @Column(nullable = false)
    private Long pid;

    @Column(name = "create_time")
    @CreationTimestamp
    private Timestamp createTime;
}
