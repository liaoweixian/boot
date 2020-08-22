package com.lwx.mybatisplus.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * extends Model  AR模式
 */
@Data
// 指定原型对应的数据库表
@TableName(value = "user")
public class User extends Model<User> implements Serializable {
    /**
     * IdType:
     *  AUTO: 数据库自增长
     *  NONE：无状态
     *  INPUT insert前自行set主键值
     *  ASSIGN_ID： 雪花算法
     *  ASSIGN_UUID： uuid
     *
     *  mp 默认认识id 使用雪花算法
     *  主键id 名字不叫id 需要使用TableId 进行声明，并指定主键策略
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
    /**
     * 直属上级id
     */
    @TableField(value = "manager_id")
    private Long managerId;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 这个字断在数据库不存在 需要排除 属于零食字断
     * 1、 transient
     * 2、 static
     * 3、@TableField(exist = false)
     */
    // 备注
    @TableField(exist = false)
    private String remark;
}
