package com.atqingke.security.service;

import com.atqingke.security.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {
    void saveUserDetails(User user);
}