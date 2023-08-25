package com.chen.myspring.common;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Chen
 * @description 实现 MethodInterceptor 方法拦截器
 * @create 2023-08-21
 */
public class WorldServiceInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("Do something before the earth explodes");
        // 调用原方法
        Object result = invocation.proceed();
        System.out.println("Do something after the earth explodes");
        return result;
    }
}
