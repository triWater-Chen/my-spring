package com.chen.myspring.beans.factory.support;

import com.chen.myspring.beans.others.BeansException;
import com.chen.myspring.core.io.Resource;
import com.chen.myspring.core.io.ResourceLoader;

/**
 * 读取 bean 定义信息，即 BeanDefinition 的接口
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String[] locations) throws BeansException;
}
