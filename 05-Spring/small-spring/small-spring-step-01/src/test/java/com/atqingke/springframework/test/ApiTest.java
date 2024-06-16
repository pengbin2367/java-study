package com.atqingke.springframework.test;

import com.atqingke.springframework.BeanDefinition;
import com.atqingke.springframework.BeanFactory;
import com.atqingke.springframework.test.bean.UserService;
import org.junit.Test;

public class ApiTest {

    @Test
    public void testBeanFactory(){
        // 1. Init BeanFactory
        BeanFactory beanFactory = new BeanFactory();
        // 2. Register Bean
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registerBeanDefinition("userService", beanDefinition);
        // 3. Get Bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}
