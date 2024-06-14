package com.gqy.usersystem.service;

import com.gqy.usersystem.povo.User;

import java.util.List;

public interface UserService {
    boolean login(String userId, String userPwd);

    String getUserIdByName(String name);

    List<User> getUsers();

    void deleteById(String deleteId);

    void updateById(User user);
}
