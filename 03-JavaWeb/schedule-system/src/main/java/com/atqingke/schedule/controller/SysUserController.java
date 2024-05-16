package com.atqingke.schedule.controller;

import com.atqingke.schedule.pojo.SysUser;
import com.atqingke.schedule.service.SysUserService;
import com.atqingke.schedule.service.impl.SysUserServiceImpl;
import com.atqingke.schedule.util.MD5Util;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user/*")
public class SysUserController extends BaseController {

    private final SysUserService sysUserService = new SysUserServiceImpl();

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        SysUser sysUser = sysUserService.findByUsername(username);
        if (sysUser == null) {
            // username error
            resp.sendRedirect("/loginUsernameError.html");
        } else if (!MD5Util.encrypt(password).equals(sysUser.getUserPwd())) {
            // password error
            resp.sendRedirect("/loginPasswordError.html");
        } else {
            // login success
            req.getSession().setAttribute("sysUser", sysUser);
            resp.sendRedirect("/showSchedule.html");
        }
    }

    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("username:" + username);
        System.out.println("password:" + password);
        SysUser sysUser = new SysUser(null, username, password);
        int rows = sysUserService.register(sysUser);
        if (rows > 0) {
            resp.sendRedirect("/registerSuccess.html");
        } else {
            resp.sendRedirect("/registerFail.html");
        }
    }
}
