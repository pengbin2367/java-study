package com.atqingke.springframework.beans.factory.support;

import com.atqingke.springframework.beans.BeansException;
import com.atqingke.springframework.core.io.Resource;
import com.atqingke.springframework.core.io.ResourceLoader;

public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

}
