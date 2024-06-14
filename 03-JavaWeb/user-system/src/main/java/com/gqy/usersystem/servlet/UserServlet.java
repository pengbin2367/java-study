package com.gqy.usersystem.servlet;

import com.gqy.usersystem.povo.User;
import com.gqy.usersystem.service.UserService;
import com.gqy.usersystem.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/user.do")
public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op=request.getParameter("op");
        System.out.println(op);
        switch (op) {
            case "0x001":
                login(request, response);
                break;
            case "0x002":
                update(request, response);
                break;
            case "0x003":
                delete(request, response);
                break;
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deleteId = request.getParameter("deleteId");
        System.out.println(deleteId);
        userService.deleteById(deleteId);
        this.getUsers(request, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String updateId = request.getParameter("updateId");
        String name = request.getParameter("name_" + updateId);
        String birth = request.getParameter("birth_" + updateId);
        String nation = request.getParameter("nation_" + updateId);
        String sex = request.getParameter("sex_" + updateId);
        String hobby = request.getParameter("hobby_" + updateId);
        String memo = request.getParameter("memo_" + updateId);
        User user = new User();
        user.setName(name);
        user.setBirth(birth);
        user.setNation(nation);
        user.setSex(sex);
        user.setHobby(hobby);
        user.setMemo(memo);
        user.setUserId(updateId);
        System.out.println(user);
        userService.updateById(user);
        this.getUsers(request, response);
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("username");
        String userId = userService.getUserIdByName(name);
        String userPwd=request.getParameter("password");
        boolean flag=userService.login(userId, userPwd);
        if(flag) {
            request.getSession().setAttribute("user", userId);
            this.getUsers(request, response);
        }
        else {
            response.getWriter().print("<script>alert('登录失败');</script>");
            response.setHeader("refresh", "0;url=login.jsp");
        }
    }

    private void getUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userService.getUsers();
        request.getSession().setAttribute("users", users);
        request.getRequestDispatcher("user/main.jsp").forward(request, response);
    }
}
