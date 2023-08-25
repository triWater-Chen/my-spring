package com.chen.myspring.aop;

public interface PointcutAdvisor extends Advisor {

    Pointcut getPointcut();
}
