package com.chen.myspring.beans.factory.support;

import com.chen.myspring.beans.factory.config.BeanDefinition;
import com.chen.myspring.beans.others.BeansException;

/**
 * BeanDefinition 注册表接口
 */
public interface BeanDefinitionRegistry {

    /**
     * 向注册表中注 BeanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 根据名称查找 BeanDefinition
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 是否包含指定名称的 BeanDefinition
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 返回定义的所有 bean 的名称
     */
    String[] getBeanDefinitionNames();
}
