package com.chen.myspring.aop;

import com.chen.myspring.aop.aspectj.AspectJExpressionPointcut;
import com.chen.myspring.service.HelloService;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author Chen
 * @description pointcut-expression
 *              切点表达式
 * @create 2023-08-21
 */
public class PointcutExpressionTest {

    @Test
    public void testPointcutExpression() throws Exception {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* com.chen.myspring.service.HelloService.*(..))");
        Class<HelloService> clazz = HelloService.class;
        Method method = clazz.getDeclaredMethod("sayHello");

        Assert.assertTrue(pointcut.matches(clazz));
        Assert.assertTrue(pointcut.matches(method, clazz));

        System.out.println("===== SUCCESS =====");
    }

}
