package com.atqingke.security.service.impl;

import com.atqingke.security.config.DBUserDetailsManager;
import com.atqingke.security.entity.User;
import com.atqingke.security.mapper.UserMapper;
import com.atqingke.security.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private DBUserDetailsManager dbUserDetailsManager;

    @Override
    public void saveUserDetails(User user) {
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withDefaultPasswordEncoder()
                .username(user.getUsername()) //自定义用户名
                .password(user.getPassword()) //自定义密码
                .build();
        dbUserDetailsManager.createUser(userDetails);
    }
}