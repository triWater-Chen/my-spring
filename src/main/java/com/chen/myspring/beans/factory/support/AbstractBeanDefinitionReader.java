package com.chen.myspring.beans.factory.support;

import com.chen.myspring.beans.others.BeansException;
import com.chen.myspring.core.io.DefaultResourceLoader;
import com.chen.myspring.core.io.Resource;
import com.chen.myspring.core.io.ResourceLoader;

/**
 * @author Chen
 * @create 2023-08-16
 */
public class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    /**
     * 实现注册 BeanDefinition 的能力
     */
    private final BeanDefinitionRegistry registry;

    /**
     * 实现获取资源的能力
     */
    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {

    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {

    }

    @Override
    public void loadBeanDefinitions(String[] locations) throws BeansException {

        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }
}
