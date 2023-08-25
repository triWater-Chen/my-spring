package com.chen.myspring.common;

import com.chen.myspring.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author Chen
 * @create 2023-08-21
 */
public class WorldServiceBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("BeforeAdvice: do something before the earth explodes");
    }
}
