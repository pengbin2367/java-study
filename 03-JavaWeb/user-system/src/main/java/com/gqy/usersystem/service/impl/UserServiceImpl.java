package com.gqy.usersystem.service.impl;

import com.gqy.usersystem.dao.UserDao;
import com.gqy.usersystem.dao.impl.UserDaoImpl;
import com.gqy.usersystem.povo.User;
import com.gqy.usersystem.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();

    @Override
    public String getUserIdByName(String name) {
        return userDao.getUserIdByName(name);
    }

    @Override
    public boolean login(String userId,String userPwd) {
        User user=userDao.getUserById(userId);
        if(user!=null) {
            return userPwd.equals(user.getUserPwd());
        }
        return false;
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public void deleteById(String deleteId) {
        userDao.deleteById(deleteId);
    }

    @Override
    public void updateById(User user) {
        userDao.updateById(user);
    }
}
