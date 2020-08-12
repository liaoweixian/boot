package com.lwx.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Table(name = "role")
@Entity
@Data
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String remark;

    private String dataScope;

    private Integer level;

    @CreationTimestamp
    private Timestamp createTime;

    private String permission;

    /**
     *  多对多关系 mappedBy 指的是把关系维护权，交给对方处理，
     *  mappedBy 在哪里 他的对立面就负责维护关系
     */
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Set<User> users;

}
