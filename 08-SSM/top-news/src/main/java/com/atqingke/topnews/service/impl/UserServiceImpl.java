package com.atqingke.topnews.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atqingke.topnews.pojo.User;
import com.atqingke.topnews.service.UserService;
import com.atqingke.topnews.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author pengbin
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2024-05-20 14:11:59
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




