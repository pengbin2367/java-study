package com.atqingke.spzx.cloud.user.service.impl;

import com.atqingke.spzx.cloud.model.entity.user.User;
import com.atqingke.spzx.cloud.user.mapper.UserMapper;
import com.atqingke.spzx.cloud.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper ;

    @Override
    public User findUserByUserId(Long userId) {
        return userMapper.findUserByUserId(userId);
    }

}