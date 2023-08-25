package com.chen.myspring.aop;

/**
 * @author Chen
 * @description 被代理对象的封装
 * @create 2023-08-21
 */
public class TargetSource {

    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    public Class<?>[] getTargetClass() {
        return this.target.getClass().getInterfaces();
    }

    public Object getTarget() {
        return this.target;
    }

}
