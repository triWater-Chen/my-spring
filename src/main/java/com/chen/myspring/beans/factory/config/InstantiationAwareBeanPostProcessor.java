package com.chen.myspring.beans.factory.config;

import com.chen.myspring.beans.others.BeansException;
import com.chen.myspring.beans.others.PropertyValues;

public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     * 在 bean 实例化之前执行
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

    /**
     * 在 bean 实例化之后，设置属性之前执行
     */
    boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException;

    /**
     * 在 bean 实例化之后，设置属性之前执行
     */
    PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException;

    /**
     * 提前暴露 bean
     */
    default Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
