package com.atqingke.security.controller;

import com.atqingke.security.entity.User;
import com.atqingke.security.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    public UserService userService;

    @PreAuthorize("hasRole('ADMIN') and authentication.name == 'admim'")
    @GetMapping("/list")
    public List<User> getList(){
        return userService.list();
    }

    @PreAuthorize("hasAuthority('USER_ADD')")
    @PostMapping("/add")
    public void add(@RequestBody User user){
        userService.saveUserDetails(user);
    }
}