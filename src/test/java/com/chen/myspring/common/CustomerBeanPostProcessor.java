package com.chen.myspring.common;

import com.chen.myspring.beans.factory.config.BeanPostProcessor;
import com.chen.myspring.beans.others.BeansException;
import com.chen.myspring.bean.Car;

/**
 * @author Chen
 * @description 自定义 BeanFactory
 *              作用在 initializeBean 部分
 * @create 2023-08-16
 */
public class CustomerBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName + "-----CustomerBeanPostProcessor#postProcessBeforeInitialization");
        // 换兰博基尼
        if ("car".equals(beanName)) {
            ((Car) bean).setBrand("lamborghini");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName + "-----CustomerBeanPostProcessor#postProcessAfterInitialization");
        return bean;
    }
}
