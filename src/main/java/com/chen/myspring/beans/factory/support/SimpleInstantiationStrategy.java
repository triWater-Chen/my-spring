package com.chen.myspring.beans.factory.support;

import com.chen.myspring.beans.factory.config.BeanDefinition;
import com.chen.myspring.beans.others.BeansException;

import java.lang.reflect.Constructor;

/**
 * @author Chen
 * @create 2023-08-15
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {

    /**
     * 简单的 bean 实例化策略，根据 bean 的无参构造函数实例化对象
     */
    @Override
    public Object instantiate(BeanDefinition beanDefinition) throws BeansException {
        Class<?> beanClass = beanDefinition.getBeanClass();
        try {
            Constructor<?> constructor = beanClass.getDeclaredConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            throw new BeansException("Failed to instantiate [" + beanClass.getName() + "]", e);
        }
    }
}
