package com.atqingke.schedule.dao;

import com.atqingke.schedule.pojo.SysUser;

public interface SysUserDao {

    SysUser findByUsername(String username);

    int register(SysUser sysUser);
}
