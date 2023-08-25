package com.chen.myspring.beans.factory.support;

import com.chen.myspring.beans.factory.config.BeanDefinition;
import com.chen.myspring.beans.others.BeansException;

/**
 * Bean 的实例化策略
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition) throws BeansException;
}
