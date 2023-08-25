package com.chen.myspring.common;

import com.chen.myspring.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * @author Chen
 * @create 2023-08-24
 */
public class WorldServiceAfterReturnAdvice implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("AfterAdvice: do something after the earth explodes");
    }
}
