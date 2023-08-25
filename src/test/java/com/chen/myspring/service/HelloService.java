package com.chen.myspring.service;

import com.chen.myspring.beans.factory.BeanFactory;
import com.chen.myspring.beans.factory.BeanFactoryAware;
import com.chen.myspring.beans.others.BeansException;
import com.chen.myspring.context.ApplicationContext;
import com.chen.myspring.context.ApplicationContextAware;

/**
 * @author Chen
 * @create 2023-08-15
 */
public class HelloService implements ApplicationContextAware, BeanFactoryAware {

    private ApplicationContext applicationContext;

    private BeanFactory beanFactory;

    public String sayHello() {
        System.out.println("hello");
        return "hello";
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
