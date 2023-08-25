package com.chen.myspring.context.support;

import com.chen.myspring.beans.factory.config.BeanPostProcessor;
import com.chen.myspring.beans.others.BeansException;
import com.chen.myspring.context.ApplicationContext;
import com.chen.myspring.context.ApplicationContextAware;

/**
 * @author Chen
 * @create 2023-08-17
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        // 若该 bean 实现了 ApplicationContextAware 接口
        // 则将会调用该 bean 类下自定义的 setApplicationContext 方法
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
