package com.gqy.usersystem.dao.impl;

import com.gqy.usersystem.dao.BaseDao;
import com.gqy.usersystem.dao.UserDao;
import com.gqy.usersystem.povo.User;

import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public String getUserIdByName(String name) {
        String sql = "select userId from user where name=?";
        User user = (User) baseQuery(User.class, sql, name).get(0);
        return user.getUserId();
    }

    @Override
    public User getUserById(String userId) {
        String sql="select * from user where userId=?";
        return (User) baseQuery(User.class, sql, userId).get(0);
    }

    @Override
    public List<User> getUsers() {
        String sql = "select * from user";
        return baseQuery(User.class, sql);
    }

    @Override
    public void deleteById(String deleteId) {
        String sql = "delete from user where userId=?";
        baseUpdate(sql, deleteId);
    }

    @Override
    public void updateById(User user) {
        String sql = "update user set name = ?, birth = ?, nation = ?, sex = ?, hobby = ?, memo = ? where userId = ?";
        baseUpdate(sql, user.getName(), user.getBirth(), user.getNation(), user.getSex(), user.getHobby(), user.getMemo(), user.getUserId());
    }
}
