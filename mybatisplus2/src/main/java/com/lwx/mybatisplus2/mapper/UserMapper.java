package com.lwx.mybatisplus2.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.lwx.mybatisplus2.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    /**
     * 自定义sql， 不会自动给你加上逻辑删除条件，
     * 需要自己手动在条件语句上添加
     * @param wrapper
     * @return
     */
    List<User> mySelectList(@Param(Constants.WRAPPER)Wrapper<User> wrapper);
}
