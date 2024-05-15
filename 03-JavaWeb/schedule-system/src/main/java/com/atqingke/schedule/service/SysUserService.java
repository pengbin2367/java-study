package com.atqingke.schedule.service;

import com.atqingke.schedule.pojo.SysUser;

public interface SysUserService {

    SysUser findByUsername(String username);

    int register(SysUser sysUser);
}
