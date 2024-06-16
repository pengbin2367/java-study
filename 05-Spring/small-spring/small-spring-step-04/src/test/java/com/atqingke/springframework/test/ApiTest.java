package com.atqingke.springframework.test;

import com.atqingke.springframework.beans.PropertyValue;
import com.atqingke.springframework.beans.PropertyValues;
import com.atqingke.springframework.beans.factory.config.BeanDefinition;
import com.atqingke.springframework.beans.factory.config.BeanReference;
import com.atqingke.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.atqingke.springframework.test.bean.UserDao;
import com.atqingke.springframework.test.bean.UserService;
import org.junit.Test;

public class ApiTest {

    @Test
    public void testBeanFactory(){
        // 1. Init BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 2. Register Bean
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("id", "2"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        beanFactory.registerBeanDefinition("userService", new BeanDefinition(UserService.class, propertyValues));
        // 3. Get Bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}
