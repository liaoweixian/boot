package com.lwx.mybatisplus2.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
    /**
     * 自动填充功能 添加属性 fill 使用 FieldFill对应的值，
     * 进行描述什么时候应该自动填充
     *
     * 在提供一个填充处理器
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
    private Integer version;

    /**
     *  // TableLogic 表示该字断为逻辑删除字断 属性value = "1", delval = "0" 一般不设置
     *     // ， 一般在配置文件里进行全局配置就好
     *   select = false 查询不显示
     */
    @TableLogic
    @TableField(select = false)
    private Integer deleted;
}
