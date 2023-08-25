package com.chen.myspring.context.support;

import com.chen.myspring.beans.factory.support.DefaultListableBeanFactory;
import com.chen.myspring.beans.others.BeansException;

/**
 * @author Chen
 * @create 2023-08-17
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    /**
     * 创建 beanFactory 并加载 BeanDefinition
     */
    protected final void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    /**
     * 创建 bean 工厂
     */
    protected DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    /**
     * 加载 BeanDefinition
     */
    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException;

    public DefaultListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
