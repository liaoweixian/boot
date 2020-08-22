package com.lwx.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lwx.mybatisplus.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseMapper<User> {

    /**
     * 自定义sql
     * @Param(ew) 名字是固定的
     * @param wrapper
     * ew 是固定的 就是Constants.WRAPPER
     */
    List<User> selectAll(@Param(Constants.WRAPPER) Wrapper<User> wrapper);

    /**
     * 自定义查询语句使用mp分页
     * 注意 第一个参数需要传 baomidou的Page对象
     * @param page
     * @param wrapper
     * @return
     */
    IPage<User> selectUserPage(Page<User> page, @Param(Constants.WRAPPER) Wrapper<User> wrapper);

    /**
     * 自定义查询语句使用mp分页
     * 注意 第一个参数需要传 baomidou的Page对象
     * @param page
     * @param wrapper
     * @return
     */
    IPage<Map<String, Object>> selectUserMapsPage(Page<Map<String, Object>> page, @Param(Constants.WRAPPER) Wrapper<User> wrapper);
}
