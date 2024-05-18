package com.atqingke.schedule.dao.impl;

import com.atqingke.schedule.dao.BaseDao;
import com.atqingke.schedule.dao.SysUserDao;
import com.atqingke.schedule.pojo.SysUser;

import java.util.List;

public class SysUserDaoImpl extends BaseDao implements SysUserDao {

    @Override
    public SysUser findByUsername(String username) {
        String sql = "select uid, username, user_pwd password from sys_user where username = ?";
        List<SysUser> userList = baseQuery(SysUser.class, sql, username);
        return null != userList && !userList.isEmpty() ? userList.get(0) : null;
    }

    @Override
    public int register(SysUser sysUser) {
        String sql = "insert into sys_user(uid, username, user_pwd) values(DEFAULT,?,?)";
        System.out.println("username:" + sysUser.getUsername());
        System.out.println("password:" + sysUser.getPassword());
        return baseUpdate(sql, sysUser.getUsername(), sysUser.getPassword());
    }
}
