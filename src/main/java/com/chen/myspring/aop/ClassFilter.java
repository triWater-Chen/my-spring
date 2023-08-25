package com.chen.myspring.aop;

/**
 * 匹配类
 */
public interface ClassFilter {

    boolean matches(Class<?> clazz);

}
