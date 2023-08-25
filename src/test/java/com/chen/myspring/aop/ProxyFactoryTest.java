package com.chen.myspring.aop;

import com.chen.myspring.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.chen.myspring.aop.framework.ProxyFactory;
import com.chen.myspring.aop.framework.adapter.AfterReturningAdviceInterceptor;
import com.chen.myspring.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import com.chen.myspring.common.WorldServiceAfterReturnAdvice;
import com.chen.myspring.common.WorldServiceBeforeAdvice;
import com.chen.myspring.service.WorldService;
import com.chen.myspring.service.WorldServiceImpl;
import org.junit.Test;

/**
 * @author Chen
 * @description lazy-init-and-multi-advice
 *              多个切面匹配同一方法
 * @create 2023-08-24
 */
public class ProxyFactoryTest {

    @Test
    public void testAdvisor() throws Exception {
        WorldService worldService = new WorldServiceImpl();

        // Advisor 是 Pointcut 和 Advice 的组合
        String expression = "execution(* com.chen.myspring.service.WorldService.explode(..))";

        // 第一个切面
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression(expression);
        MethodBeforeAdviceInterceptor methodInterceptor = new MethodBeforeAdviceInterceptor(new WorldServiceBeforeAdvice());
        advisor.setAdvice(methodInterceptor);

        // 第二个切面
        AspectJExpressionPointcutAdvisor advisor1 = new AspectJExpressionPointcutAdvisor();
        advisor1.setExpression(expression);
        AfterReturningAdviceInterceptor afterReturningAdviceInterceptor = new AfterReturningAdviceInterceptor(new WorldServiceAfterReturnAdvice());
        advisor1.setAdvice(afterReturningAdviceInterceptor);

        // 通过 ProxyFactory 来获得代理
        ProxyFactory factory = new ProxyFactory();
        TargetSource targetSource = new TargetSource(worldService);
        factory.setTargetSource(targetSource);
        // 使用 CGLIB
        factory.setProxyTargetClass(true);
        factory.addAdvisor(advisor);
        factory.addAdvisor(advisor1);
        WorldService proxy = (WorldService) factory.getProxy();
        proxy.explode();

        System.out.println("===== SUCCESS =====");
    }
}
