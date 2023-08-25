package com.chen.myspring.aop.framework;

import com.chen.myspring.aop.AdvisedSupport;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 获得拦截器链接口
 */
public interface AdvisorChainFactory {

    List<Object> getInterceptorsAndDynamicInterceptionAdvice(AdvisedSupport config, Method method, Class<?> targetClass);
}
