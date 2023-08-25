package com.chen.myspring.beans.factory.support;

import com.chen.myspring.beans.factory.DisposableBean;
import com.chen.myspring.beans.factory.ObjectFactory;
import com.chen.myspring.beans.factory.config.SingletonBeanRegistry;
import com.chen.myspring.beans.others.BeansException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Chen
 * @create 2023-08-15
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * 一级缓存
     */
    private Map<String, Object> singletonObjects = new HashMap<>();

    /**
     * 二级缓存
     */
    protected Map<String, Object> earlySingletonObjects = new HashMap<>();

    /**
     * 三级缓存
     */
    private Map<String, ObjectFactory<?>> singletonFactories = new HashMap<String, ObjectFactory<?>>();


    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {

        // 从一级中获取
        Object singletonObject = singletonObjects.get(beanName);

        if (singletonObject == null) {
            // 从二级中获取
            singletonObject = earlySingletonObjects.get(beanName);

            if (singletonObject == null) {
                // 从三级中获取
                ObjectFactory<?> singletonFactory = singletonFactories.get(beanName);

                if (singletonFactory != null) {
                    // 获取对应代理对象
                    singletonObject = singletonFactory.getObject();
                    // 从三级缓存放进二级缓存
                    // 此时被放入二级缓存的是 正在创建的 bean 的代理对象
                    earlySingletonObjects.put(beanName, singletonObject);
                    singletonFactories.remove(beanName);
                }
            }
        }
        return singletonObject;
    }

    public void addSingleton(String beanName, Object singletonObject) {
        // 直接放入一级缓存，删除二、三级
        singletonObjects.put(beanName, singletonObject);
        earlySingletonObjects.remove(beanName);
        singletonFactories.remove(beanName);
    }

    protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory) {
        singletonFactories.put(beanName, singletonFactory);
    }

    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

    public void destroySingletons() {
        Set<String> beanNames = disposableBeans.keySet();
        for (String beanName : beanNames) {
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }
}
