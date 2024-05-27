package com.atqingke.spzx.cloud.user.service;

import com.atqingke.spzx.cloud.model.entity.user.User;

public interface UserService {

    // 根据用户的id查询用户详情
    User findUserByUserId(Long userId) ;

}