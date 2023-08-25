package com.chen.myspring.context.support;

import com.chen.myspring.beans.factory.support.DefaultListableBeanFactory;
import com.chen.myspring.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author Chen
 * @create 2023-08-17
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (configLocations != null) {
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
