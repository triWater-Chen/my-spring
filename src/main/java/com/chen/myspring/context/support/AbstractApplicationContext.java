package com.chen.myspring.context.support;

import com.chen.myspring.beans.factory.ConfigurableListableBeanFactory;
import com.chen.myspring.beans.factory.config.BeanFactoryPostProcessor;
import com.chen.myspring.beans.factory.config.BeanPostProcessor;
import com.chen.myspring.beans.others.BeansException;
import com.chen.myspring.context.ApplicationEvent;
import com.chen.myspring.context.ApplicationListener;
import com.chen.myspring.context.ConfigurableApplicationContext;
import com.chen.myspring.context.event.ApplicationEventMulticaster;
import com.chen.myspring.context.event.ContextClosedEvent;
import com.chen.myspring.context.event.ContextRefreshedEvent;
import com.chen.myspring.context.event.SimpleApplicationEventMulticaster;
import com.chen.myspring.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

/**
 * @author Chen
 * @description 抽象应用上下文
 * @create 2023-08-17
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

    public abstract ConfigurableListableBeanFactory getBeanFactory();

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(requiredType);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public void refresh() throws BeansException {

        // 创建 BeanFactory，并从 xml 中读取并加载 BeanDefinition
        refreshBeanFactory();
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 在 bean 实例化之前，执行 BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);

        // 添加 ApplicationContextAwareProcessor ，让继承自 ApplicationContextAware 的 bean 能感知 bean
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        // BeanPostProcessor 需要提前于其他 bean 实例化之前注册
        registerBeanPostProcessors(beanFactory);

        // 初始化事件发布者
        initApplicationEventMulticaster();

        // 注册事件监听器
        registerListeners();

        // 提前实例化单例 bean
        beanFactory.preInstantiateSingletons();

        // 发布容器刷新事件
        finishRefresh();
    }

    /**
     * 创建 BeanFactory，并加载 BeanDefinition
     */
    protected abstract void refreshBeanFactory() throws BeansException;

    /**
     * 在 bean 实例化之前，执行 BeanFactoryPostProcessor
     */
    protected void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    /**
     * 注册 BeanPostProcessor
     */
    protected void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    /**
     * 初始化事件发布者
     */
    protected void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.addSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }

    /**
     * 注册事件监听器
     */
    protected void registerListeners() {
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener applicationListener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(applicationListener);
        }
    }

    /**
     * 发布容器刷新完成事件
     */
    protected void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    /**
     * 执行事件
     */
    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    public void close() {
        doClose();
    }

    public void registerShutdownHook() {
        Thread shutdownHook = new Thread(this::doClose);
        Runtime.getRuntime().addShutdownHook(shutdownHook);

    }

    protected void doClose() {

        // 发布容器关闭事件
        publishEvent(new ContextClosedEvent(this));

        // 执行单例 bean 的销毁方法
        destroyBeans();
    }

    protected void destroyBeans() {
        getBeanFactory().destroySingletons();
    }

}
