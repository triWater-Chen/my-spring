package com.chen.myspring.context;

import com.chen.myspring.beans.factory.Aware;
import com.chen.myspring.beans.others.BeansException;

/**
 * 实现该接口，能感知所属 ApplicationContext
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
