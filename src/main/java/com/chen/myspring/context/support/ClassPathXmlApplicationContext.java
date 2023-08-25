package com.chen.myspring.context.support;

import com.chen.myspring.beans.others.BeansException;

/**
 * @author Chen
 * @description xml 文件的应用上下文
 * @create 2023-08-17
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    private String[] configLocations;

    /**
     * 从 xml 文件加载 BeanDefinition，并且自动刷新上下文
     */
    public ClassPathXmlApplicationContext(String configLocation) throws BeansException {
        this(new String[]{configLocation});
    }

    /**
     * 从 xml 文件加载 BeanDefinition，并且自动刷新上下文
     */
    public ClassPathXmlApplicationContext(String[] configLocations) throws BeansException {
        this.configLocations = configLocations;
        refresh();
    }

    protected String[] getConfigLocations() {
        return this.configLocations;
    }
}
