package com.chen.myspring.context.event;

import com.chen.myspring.beans.factory.BeanFactory;
import com.chen.myspring.beans.factory.BeanFactoryAware;
import com.chen.myspring.beans.others.BeansException;
import com.chen.myspring.context.ApplicationEvent;
import com.chen.myspring.context.ApplicationListener;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Chen
 * @create 2023-08-18
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {

    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new HashSet<>();

    private BeanFactory beanFactory;

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.remove(listener);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

}
