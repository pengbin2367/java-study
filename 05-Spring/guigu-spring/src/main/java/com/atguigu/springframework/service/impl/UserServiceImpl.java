package com.atguigu.springframework.service.impl;

import com.atguigu.springframework.anno.Bean;
import com.atguigu.springframework.anno.Di;
import com.atguigu.springframework.dao.UserDao;
import com.atguigu.springframework.service.UserService;

@Bean
public class UserServiceImpl implements UserService {

    @Di
    private UserDao userDao;

    @Override
    public void serviceFunc() {
        System.out.println("serviceFunc......");
    }
}
