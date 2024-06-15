package com.atqingke.springframework.test;

import com.atqingke.springframework.beans.factory.config.BeanDefinition;
import com.atqingke.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.atqingke.springframework.test.Bean.UserService;
import org.junit.Test;

public class ApiTest {

    @Test
    public void testBeanFactory(){
        // 1. Init BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 2. Register Bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);
        // 3. Get Bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
        UserService userService1 = (UserService) beanFactory.getBean("userService");
        userService1.queryUserInfo();
        System.out.println(userService == userService1);
    }
}
