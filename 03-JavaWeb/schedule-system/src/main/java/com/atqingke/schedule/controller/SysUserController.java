package com.atqingke.schedule.controller;

import com.atqingke.schedule.common.Result;
import com.atqingke.schedule.common.ResultCodeEnum;
import com.atqingke.schedule.pojo.SysUser;
import com.atqingke.schedule.service.SysUserService;
import com.atqingke.schedule.service.impl.SysUserServiceImpl;
import com.atqingke.schedule.util.MD5Util;
import com.atqingke.schedule.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/user/*")
public class SysUserController extends BaseController {

    private final SysUserService sysUserService = new SysUserServiceImpl();

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SysUser loginUser = WebUtil.readJson(req, SysUser.class);
        SysUser sysUser = sysUserService.findByUsername(loginUser.getUsername());
        Result result = null;
        if (sysUser == null) {
            // username error
            result = Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        } else if (!MD5Util.encrypt(loginUser.getPassword()).equals(sysUser.getPassword())) {
            // password error
            result = Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
        } else {
            // login success
            Map data = new HashMap();
            sysUser.setPassword("");
            data.put("loginUser", sysUser);
            result = Result.ok(data);
        }
        WebUtil.writeJson(resp, result);
    }

    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SysUser sysUser = WebUtil.readJson(req, SysUser.class);
        System.out.println(sysUser);
        int rows = sysUserService.register(sysUser);
        Result result = null;
        if (rows < 1) {
            result = Result.build(null, ResultCodeEnum.USERNAME_USED);
        } else {
            result = Result.ok("success");
        }
        WebUtil.writeJson(resp, result);
    }

    protected void checkUsernameUsed(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        SysUser sysUser = sysUserService.findByUsername(username);
        Result result = Result.ok(null);
        if (sysUser != null) {
            result = Result.build(null, ResultCodeEnum.USERNAME_USED);
        }
        WebUtil.writeJson(resp, result);
    }
}
