package com.chen.myspring.beans.factory.config;

import com.chen.myspring.beans.factory.ConfigurableListableBeanFactory;
import com.chen.myspring.beans.others.BeansException;

/**
 * 允许自定义修改 BeanDefinition 的属性值（bean 实例化之前）
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有 BeanDefinition 加载完成后，但在 bean 实例化之前，提供修改 BeanDefinition 属性值的机制
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
