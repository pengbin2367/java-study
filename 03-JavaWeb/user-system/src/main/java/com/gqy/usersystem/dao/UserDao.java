package com.gqy.usersystem.dao;

import com.gqy.usersystem.povo.User;

import java.util.List;

public interface UserDao {
    User getUserById(String userId);

    String getUserIdByName(String name);

    List<User> getUsers();

    void deleteById(String deleteId);

    void updateById(User user);
}
