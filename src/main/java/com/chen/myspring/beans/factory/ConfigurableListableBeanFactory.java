package com.chen.myspring.beans.factory;

import com.chen.myspring.beans.factory.config.AutowireCapableBeanFactory;
import com.chen.myspring.beans.factory.config.BeanDefinition;
import com.chen.myspring.beans.factory.config.ConfigurableBeanFactory;
import com.chen.myspring.beans.others.BeansException;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    /**
     * 根据名称查找 BeanDefinition
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 提前实例化所有单例实例
     */
    void preInstantiateSingletons() throws BeansException;

}
