package com.atguigu.springframework;

import com.atguigu.springframework.bean.AnnotationApplicationContext;
import com.atguigu.springframework.service.UserService;
import org.junit.Test;

public class ApiTest {

    @Test
    public void test() {
        AnnotationApplicationContext context = new AnnotationApplicationContext("com.atguigu.springframework");
        UserService userService = (UserService) context.getBean(UserService.class);
        userService.serviceFunc();
    }
}
