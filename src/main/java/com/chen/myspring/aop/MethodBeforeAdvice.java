package com.chen.myspring.aop;

import java.lang.reflect.Method;

/**
 * 前置增强
 */
public interface MethodBeforeAdvice extends BeforeAdvice {

    void before(Method method, Object[] args, Object target) throws Throwable;
}
