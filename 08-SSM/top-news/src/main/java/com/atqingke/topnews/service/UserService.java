package com.atqingke.topnews.service;

import com.atqingke.topnews.pojo.User;
import com.atqingke.topnews.util.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author pengbin
* @description 针对表【news_user】的数据库操作Service
* @createDate 2024-05-20 14:11:59
*/
public interface UserService extends IService<User> {

    Result login(User user);

    Result getUserInfo(String token);

    Result checkUserName(String username);

    Result regist(User user);
}
