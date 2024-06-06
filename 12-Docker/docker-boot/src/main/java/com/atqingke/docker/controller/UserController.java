package com.atqingke.docker.controller;

import cn.hutool.core.util.IdUtil;
import com.atqingke.docker.pojo.User;
import com.atqingke.docker.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RequestMapping("admin/user")
@RestController
@Api(description = "用户User接口")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation("数据库新增3条记录")
    @PostMapping("add")
    public void addUser() {
        for (int i = 1; i <=3; i++) {
            User user = new User();
            user.setUsername("wfc"+i);
            user.setPassword(IdUtil.simpleUUID().substring(0,6));
            user.setSex((byte) new Random().nextInt(2));
            userService.addUser(user);
        }
    }

    @ApiOperation("查询1条记录")
    @GetMapping(value = "find/{id}")
    public User findUserById(@PathVariable Integer id)
    {
        return userService.findUserById(id);
    }
}