package com.chen.myspring.aop.aspectj;

import com.chen.myspring.aop.Pointcut;
import com.chen.myspring.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * @author Chen
 * @description aspectJ 表达式的 advisor
 * @create 2023-08-21
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    /**
     * 用于捕获 JoinPoint
     */
    private AspectJExpressionPointcut pointcut;

    /**
     * 决定在 JoinPoint 执行某种操作
     * BeforeAdvice/AfterAdvice/AfterReturningAdvice/ThrowsAdvice...
     */
    private Advice advice;

    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public Pointcut getPointcut() {
        if (pointcut == null) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }
}
