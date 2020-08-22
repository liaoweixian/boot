package com.lwx.mybatisplus.controller;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lwx.mybatisplus.domain.User;
import com.lwx.mybatisplus.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void select() {
        List<User> users = userMapper.selectList(null);
        Assert.assertEquals(5, users.size());
        users.forEach(System.out::println);
    }

    /**
     * 这里有个地方要注意
     * 1/这里没有插入email字断
     * Preparing: INSERT INTO user ( id, name, age, manager_id, create_time ) VALUES ( ?, ?, ?, ?, ? )
     * 在插入或修改时，mp默认为 字断为null，进行排除
     * mp 对主键id，默认使用雪花设置主键id
     * mp 对字断默认进行驼峰表达式
     */
    @Test
    public void insert() {
        User user = new User();
        user.setName("向化");
        user.setAge(23);
        user.setEmail("965932944@qq.com");
        user.setManagerId(1088248166370832385L);
        user.setCreateTime(LocalDateTime.now());
        user.setRemark("我是一个备注信息");
        int rows = userMapper.insert(user);
        System.out.println(rows);
    }

    @Test
    public void selectById() {
        User user = userMapper.selectById(1088248166370832385L);
        System.out.println(user);
    }

    @Test
    public void selectByIds() {
        List<Long> longs = Arrays.asList(1094590409767661570l, 1088248166370832385l, 1296457014901297154l);
        List<User> users = userMapper.selectBatchIds(longs);
        users.forEach(System.out::println);
    }

    /**
     * Map的key是表中的字断，不是domain中的元素
     */
    @Test
    public void selectByMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "王天风");
        map.put("age", 25);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    /**
     * 1、名字中包含雨并且年龄小于40
     * name like '%雨%' and age < 40
     */
    @Test
    public void selectByWrapper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // QueryWrapper<User> query = Wrappers.query();
        queryWrapper.like("name", "雨")
                .lt("age", 40);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 名字包含雨并且年龄大于等于20且小于等于40并且email不为空
     * name like '%雨%' and age between 20 and 40 and email is not null
     */
    @Test
    public void selectByWrapper2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // QueryWrapper<User> query = Wrappers.query();
        queryWrapper.like("name", "雨")
                .between("age", 20, 40)
                .isNotNull("email");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 名字为王姓或者年龄大于25， 按照年龄降序排序，年龄相同按照id升序排序
     * name like '王%' or age >= 25 order by age desc, id asc
     */
    @Test
    public void selectByWrapper3() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // QueryWrapper<User> query = Wrappers.query();
        queryWrapper.likeLeft("name", "王")
                .or()
                .ge("age", 25)
                .orderByDesc("age")
                .orderByAsc("id");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 创建日期为2019年2月14日并且指数上级为王姓
     * date_format(create_time, '%Y-%m-%d') and manager_id in (select id from user where name like '王%')
     */
    @Test
    public void selectByWrapper4() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply("date_format(create_time, {0})", "2019-02-14")
                .inSql("manager_id", "select id from user where name like '王%'");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByWrapper5() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "王")
                .and(wp->wp.between("age", 20, 40).isNotNull("email"));
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * （年龄小于40或邮箱不为空）并且名字为王姓
     * （age < 40 or email is not null）and name like '王%'
     */
    @Test
    public void selectByWrapper6() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.nested(mp->mp.lt("age", 40).or().isNotNull("email"))
                .likeRight("name", "王").last("limit 1");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 1、名字中包含雨并且年龄小于40
     * name like '%雨%' and age < 40
     * 查询出指定的列
     */
    @Test
    public void selectByWrapperSupper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // QueryWrapper<User> query = Wrappers.query();
        queryWrapper.select("id", "name as name").like("name", "雨")
                .lt("age", 40);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * allEq(map, boolean)
     * true null参数不会忽略
     * false null参数会忽略
     */
    @Test
    public void selectByWrapperSupperAllEq() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "王天风");
        map.put("age", null);
        queryWrapper.allEq(map);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByWrapperMaps() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // QueryWrapper<User> query = Wrappers.query();
        queryWrapper.like("name", "雨")
                .lt("age", 40);
        List<Map<String, Object>> list = userMapper.selectMaps(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * 条件构造器
     */
    @Test
    public void selectLambda() {
        // final LambdaQueryWrapper<User> lambda = new QueryWrapper<User>().lambda();
        // final LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.like(User::getName, "雨")
                .lt(User::getAge, 40);
        List<User> users = userMapper.selectList(lambdaQuery);
        users.forEach(System.out::println);
    }

    /**
     * 条件构造器
     */
    @Test
    public void selectLambda2() {
        // final LambdaQueryWrapper<User> lambda = new QueryWrapper<User>().lambda();
        // final LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.like(User::getName, "雨")
                .lt(User::getAge, 40);
        List<User> users = userMapper.selectAll(lambdaQuery);
        users.forEach(System.out::println);
    }

    /**
     * 分页
     * 当前页 1 就是代表数据库 limit 0下标
     * 分页提供了两种，一种返回 实体， 一种返回Map
     * page 对象的第三个参数，传true 查总记录数 传false 不查询总记录数
     */
    @Test
    public void selectPage() {
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.ge(User::getAge, 26);
        Page<Map<String, Object>> userPage = new Page<>(1, 2);
//        IPage<User> iPage = userMapper.selectPage(userPage, lambdaQuery);
//        System.out.println("总页数："+iPage.getPages());
//        System.out.println("总条数："+iPage.getTotal());
//        List<User> userList = iPage.getRecords();
        IPage<Map<String, Object>> iPage = userMapper.selectMapsPage(userPage, lambdaQuery);
        System.out.println("总页数："+iPage.getPages());
        System.out.println("总条数："+iPage.getTotal());
        List<Map<String, Object>> userList = iPage.getRecords();
        userList.forEach(System.out::println);
    }

    /**
     * 自定义sql 使用 mp插件分页
     */
    @Test
    public void selectUserPage() {
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.ge(User::getAge, 26);
        Page<User> userPage = new Page<>(1, 2);
        IPage<User> iPage = userMapper.selectUserPage(userPage, lambdaQuery);
        System.out.println("总页数："+iPage.getPages());
        System.out.println("总条数："+iPage.getTotal());
        List<User> userList = iPage.getRecords();
        userList.forEach(System.out::println);
    }

    /**
     * 自定义sql 使用 mp插件分页 返回Map结果集
     */
    @Test
    public void selectUserMpasPage() {
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.ge(User::getAge, 26);
        Page<Map<String, Object>> userPage = new Page<>(1, 2, false);
        IPage<Map<String, Object>> iPage = userMapper.selectUserMapsPage(userPage, lambdaQuery);
        System.out.println("总页数："+iPage.getPages());
        System.out.println("总条数："+iPage.getTotal());
        List<Map<String, Object>> userList = iPage.getRecords();
        userList.forEach(System.out::println);
    }
    
}
