package com.atqingke.springframework.beans.factory;

import com.atqingke.springframework.beans.BeansException;

public interface BeanFactory {

    public Object getBean(String beanName) throws BeansException;

    public Object getBean(String beanName, Object ...args) throws BeansException;
}
