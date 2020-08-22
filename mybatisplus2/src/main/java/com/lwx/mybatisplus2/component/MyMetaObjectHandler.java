package com.lwx.mybatisplus2.component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自动填充处理器
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        System.out.println("--------insertFill");
        boolean createTime = metaObject.hasSetter("createTime");
        if (createTime) {
            setInsertFieldValByName("createTime", LocalDateTime.now(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        System.out.println("--------updateFill");
        /**
         * 如果没有手动填充值，才进行自动填充值，否则使用手动填充的值
         */
        Object val = getFieldValByName("updateTime", metaObject);
        if (val == null) {
            setUpdateFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
    }
}
