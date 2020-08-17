package com.lwx.mybatisplus.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "user")
public class User implements Serializable {
    /**
     * IdType:
     *  AUTO: 数据库自增长
     *  NONE：无状态
     *  INPUT insert前自行set主键值
     *  ASSIGN_ID： 雪花算法
     *  ASSIGN_UUID： uuid
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * exist: 是否为数据库表字断， 默认true
     */
    @TableField(value = "name", exist = true)
    private String name;
    private Integer age;
    private String email;
}
