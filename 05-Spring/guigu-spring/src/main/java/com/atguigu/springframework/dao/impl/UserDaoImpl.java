package com.atguigu.springframework.dao.impl;

import com.atguigu.springframework.anno.Bean;
import com.atguigu.springframework.dao.UserDao;

@Bean
public class UserDaoImpl implements UserDao {

    public void daoFunc() {
        System.out.println("UserDaoImpl.daoFunc......");
    }
}
