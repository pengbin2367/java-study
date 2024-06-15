package com.atqingke.springframework.test;

import com.atqingke.springframework.beans.factory.config.BeanDefinition;
import com.atqingke.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.atqingke.springframework.test.Bean.UserService;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ApiTest {

    @Test
    public void testBeanFactory(){
        // 1. Init BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 2. Register Bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);
        // 3. Get Bean
        UserService userService = (UserService) beanFactory.getBean("userService", "pengbin");
        userService.queryUserInfo();
    }

    @Test
    public void testNewInstance() throws IllegalAccessException, InstantiationException {
        UserService userService = UserService.class.newInstance();
        System.out.println(userService);
    }

    @Test
    public void testContructor() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<UserService> userServiceClass = UserService.class;
        Constructor<UserService> declaredConstructor = userServiceClass.getDeclaredConstructor(String.class);
        UserService userService = declaredConstructor.newInstance("QingKe");
        System.out.println(userService);
    }
}
