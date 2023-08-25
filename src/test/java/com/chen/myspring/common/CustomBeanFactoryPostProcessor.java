package com.chen.myspring.common;

import com.chen.myspring.beans.factory.ConfigurableListableBeanFactory;
import com.chen.myspring.beans.factory.config.BeanDefinition;
import com.chen.myspring.beans.factory.config.BeanFactoryPostProcessor;
import com.chen.myspring.beans.others.BeansException;
import com.chen.myspring.beans.others.PropertyValue;
import com.chen.myspring.beans.others.PropertyValues;

/**
 * @author Chen
 * @description 自定义 BeanFactory
 * @create 2023-08-16
 */
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        BeanDefinition personBeanDefinition = beanFactory.getBeanDefinition("person");
        PropertyValues propertyValues = personBeanDefinition.getPropertyValues();
        // 将 person 的 name 属性改为 Bob（覆盖原有值）
        propertyValues.addPropertyValue(new PropertyValue("name", "Bob"));
    }
}
