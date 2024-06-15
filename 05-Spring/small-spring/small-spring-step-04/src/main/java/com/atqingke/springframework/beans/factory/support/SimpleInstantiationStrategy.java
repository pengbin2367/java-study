package com.atqingke.springframework.beans.factory.support;

import com.atqingke.springframework.beans.BeansException;
import com.atqingke.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SimpleInstantiationStrategy implements InstantiationStrategy {
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor cotr, Object[] args) throws BeansException {
        Class beanClass = beanDefinition.getBeanClass();
        try {
            if (cotr == null) {
                return beanClass.getDeclaredConstructor().newInstance();
            } else {
                return beanClass.getDeclaredConstructor(cotr.getParameterTypes()).newInstance(args);
            }
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new BeansException("Failed to instantiate " + beanName, e);
        }
    }
}
