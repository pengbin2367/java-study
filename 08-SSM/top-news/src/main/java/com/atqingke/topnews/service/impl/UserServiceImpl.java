package com.atqingke.topnews.service.impl;

import com.atqingke.topnews.mapper.UserMapper;
import com.atqingke.topnews.pojo.User;
import com.atqingke.topnews.service.UserService;
import com.atqingke.topnews.util.JwtHelper;
import com.atqingke.topnews.util.MD5Util;
import com.atqingke.topnews.util.Result;
import com.atqingke.topnews.util.ResultCodeEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @author pengbin
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2024-05-20 14:11:59
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 登录业务实现
     *
     * @param user
     * @return result封装
     */
    @Override
    public Result login(User user) {

        //根据账号查询
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, user.getUsername());
        User loginUser = userMapper.selectOne(queryWrapper);

        //账号判断
        if (loginUser == null) {
            //账号错误
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }

        //判断密码
        if (!StringUtils.isEmpty(user.getUserPwd())
                && loginUser.getUserPwd().equals(MD5Util.encrypt(user.getUserPwd()))) {
            //账号密码正确
            //根据用户唯一标识生成token
            String token = jwtHelper.createToken(Long.valueOf(loginUser.getUid()));

            Map data = new HashMap();
            data.put("token", token);

            return Result.ok(data);
        }

        //密码错误
        return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
    }

    @Override
    public Result getUserInfo(String token) {

        //1.判定是否有效期
        if (jwtHelper.isExpiration(token)) {
            //true过期,直接返回未登录
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }

        //2.获取token对应的用户
        int userId = jwtHelper.getUserId(token).intValue();

        //3.查询数据
        User user = userMapper.selectById(userId);

        if (user != null) {
            user.setUserPwd(null);
            Map data = new HashMap();
            data.put("loginUser", user);
            return Result.ok(data);
        }

        return Result.build(null, ResultCodeEnum.NOTLOGIN);
    }

    @Override
    public Result checkUserName(String username) {

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User user = userMapper.selectOne(queryWrapper);

        if (user != null) {
            return Result.build(null, ResultCodeEnum.USERNAME_USED);
        }

        return Result.ok(null);
    }

    @Override
    public Result regist(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,user.getUsername());
        Long count = userMapper.selectCount(queryWrapper);

        if (count > 0){
            return Result.build(null,ResultCodeEnum.USERNAME_USED);
        }

        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));
        int rows = userMapper.insert(user);
        System.out.println("rows = " + rows);
        return Result.ok(null);
    }
}