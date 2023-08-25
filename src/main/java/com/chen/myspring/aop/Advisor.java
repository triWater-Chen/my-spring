package com.chen.myspring.aop;

import org.aopalliance.aop.Advice;

/**
 * 包含一个 Pointcut 和一个 Advice 的组合
 * Pointcut 用于捕获 JoinPoint，Advice 决定在 JoinPoint 执行某种操作
 */
public interface Advisor {

    Advice getAdvice();
}
