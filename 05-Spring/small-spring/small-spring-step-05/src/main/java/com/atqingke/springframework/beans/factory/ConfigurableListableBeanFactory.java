package com.atqingke.springframework.beans.factory;

import com.atqingke.springframework.beans.BeansException;
import com.atqingke.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.atqingke.springframework.beans.factory.config.BeanDefinition;
import com.atqingke.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * Configuration interface to be implemented by most listable bean factories.
 * In addition to {@link ConfigurableBeanFactory}, it provides facilities to
 * analyze and modify bean definitions, and to pre-instantiate singletons.
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

}
