package com.atqingke.spzx.cloud.user.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.atqingke.spzx.cloud.model.entity.user.User;
import com.atqingke.spzx.cloud.user.properties.PatternProperties;
import com.atqingke.spzx.cloud.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService ;

//    @Value("${pattern.dateformat}")
//    private String pattern ;

//    @Autowired
//    PatternProperties patternProperties;

    @SentinelResource("hot")
    @GetMapping(value = "/findUserByUserId/{userId}")
    public User findUserByUserId(@PathVariable(value = "userId") Long userId , @RequestHeader(name = "Truth")String header) {
        log.info("UserController...findUserByUserId方法执行了... ,header: {}" , header);
        return userService.findUserByUserId(userId) ;
    }

}