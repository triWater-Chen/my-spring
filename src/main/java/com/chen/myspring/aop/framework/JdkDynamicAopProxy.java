package com.chen.myspring.aop.framework;

import com.chen.myspring.aop.AdvisedSupport;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @author Chen
 * @description 基于 JDK 动态代理的具体实现
 * @create 2023-08-21
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

    private final AdvisedSupport advised;

    public JdkDynamicAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    /**
     * 返回代理对象
     */
    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(getClass().getClassLoader(), advised.getTargetSource().getTargetClass(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 获取目标对象
        Object target = advised.getTargetSource().getTarget();
        Class<?> targetClass = target.getClass();
        Object retVal;

        // 获取拦截器链（得到所有符合当前对象的 Interceptor 及其对应 Advice）
        List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
        if (chain == null || chain.isEmpty()) {
            return method.invoke(target, args);
        } else {

            // 将拦截器统一封装成 ReflectiveMethodInvocation
            MethodInvocation invocation = new ReflectiveMethodInvocation(proxy, target, method, args, targetClass, chain);
            // Proceed to the joinPoint through the interceptor chain.
            // 执行拦截器链（具体顺序在各拦截器的 invoke 方法中实现）
            retVal = invocation.proceed();
        }
        return retVal;
    }
}
