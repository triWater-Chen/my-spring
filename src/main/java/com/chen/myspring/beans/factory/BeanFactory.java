package com.chen.myspring.beans.factory;

import com.chen.myspring.beans.others.BeansException;

/**
 * Bean 容器
 */
public interface BeanFactory {

    /**
     * 获取 bean
     */
    Object getBean(String name) throws BeansException;

    /**
     * 根据名称和类型查找 bean
     */
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

    /**
     * 根据类型查找 bean
     */
    <T> T getBean(Class<T> requiredType) throws BeansException;
}
