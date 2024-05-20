package com.atqingke.topnews.controller;

import com.atqingke.topnews.pojo.User;
import com.atqingke.topnews.service.UserService;
import com.atqingke.topnews.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public Result login(@RequestBody User user) {
        return userService.login(user);
    }

    @GetMapping("getUserInfo")
    public Result getUserInfo(@RequestParam("token") String token) {
        return userService.getUserInfo(token);
    }

    @PostMapping("checkUserName")
    public Result checkUserName(String username) {
        return userService.checkUserName(username);
    }

    @PostMapping("regist")
    public Result regist(@RequestBody User user) {
        return userService.regist(user);
    }
}
