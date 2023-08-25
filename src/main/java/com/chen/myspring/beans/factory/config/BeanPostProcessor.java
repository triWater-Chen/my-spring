package com.chen.myspring.beans.factory.config;

import com.chen.myspring.beans.others.BeansException;

/**
 * 用于修改实例化后的 bean 的修改扩展点
 */
public interface BeanPostProcessor {

    /**
     * 在 bean 执行初始化方法之前执行此方法
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 在 bean 执行初始化方法之后执行此方法
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
