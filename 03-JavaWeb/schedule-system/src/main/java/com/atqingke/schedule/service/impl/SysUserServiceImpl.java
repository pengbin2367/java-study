package com.atqingke.schedule.service.impl;

import com.atqingke.schedule.dao.SysUserDao;
import com.atqingke.schedule.dao.impl.SysUserDaoImpl;
import com.atqingke.schedule.pojo.SysUser;
import com.atqingke.schedule.service.SysUserService;
import com.atqingke.schedule.util.MD5Util;

public class SysUserServiceImpl implements SysUserService {

    private final SysUserDao sysUserDao = new SysUserDaoImpl();

    @Override
    public SysUser findByUsername(String username) {
        return sysUserDao.findByUsername(username);
    }

    @Override
    public int register(SysUser sysUser) {
        sysUser.setUserPwd(MD5Util.encrypt(sysUser.getUserPwd()));
        return sysUserDao.register(sysUser);
    }
}
