package com.chen.myspring.beans.factory;

import com.chen.myspring.beans.others.BeansException;

/**
 * 实现该接口，能感知所属 BeanFactory
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
