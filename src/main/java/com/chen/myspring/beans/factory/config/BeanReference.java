package com.chen.myspring.beans.factory.config;

/**
 * @author Chen
 * @description 一个 bean 对另一个 bean 的引用
 * @create 2023-08-16
 */
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

}
