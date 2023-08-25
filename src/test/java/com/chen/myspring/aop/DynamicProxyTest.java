package com.chen.myspring.aop;

import com.chen.myspring.aop.aspectj.AspectJExpressionPointcut;
import com.chen.myspring.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.chen.myspring.aop.framework.CglibAopProxy;
import com.chen.myspring.aop.framework.JdkDynamicAopProxy;
import com.chen.myspring.aop.framework.ProxyFactory;
import com.chen.myspring.aop.framework.adapter.AfterReturningAdviceInterceptor;
import com.chen.myspring.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import com.chen.myspring.common.WorldServiceAfterReturnAdvice;
import com.chen.myspring.common.WorldServiceBeforeAdvice;
import com.chen.myspring.common.WorldServiceInterceptor;
import com.chen.myspring.service.WorldService;
import com.chen.myspring.service.WorldServiceImpl;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Chen
 * @description jdk-dynamic-proxy：基于 JDK 的动态代理，在运行期间为接口生成对象的代理对象
 *              cglib-dynamic-proxy：基于 CGLIB 的动态代理，在运行期间动态构建字节码的 class 文件，为类生成子类
 *              proxy-factory：AOP 代理工厂，决定使用 JDK 动态代理还是 CGLIB 动态代理
 *              common-advice：BeforeAdvice
 *              pointcut-advisor：PointcutAdvisor，Pointcut和Advice的组合
 *
 *              修改为 lazy-init-and-multi-advice
 * @create 2023-08-21
 */
public class DynamicProxyTest {

    private AdvisedSupport advisedSupport;

    @Before
    public void setup() {
        WorldService worldService = new WorldServiceImpl();

        advisedSupport = new ProxyFactory();
        // Advisor 是 Pointcut 和 Advice 的组合
        String expression = "execution(* com.chen.myspring.service.WorldService.explode(..))";
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression(expression);
        AfterReturningAdviceInterceptor methodInterceptor = new AfterReturningAdviceInterceptor(new WorldServiceAfterReturnAdvice());
        advisor.setAdvice(methodInterceptor);
        TargetSource targetSource = new TargetSource(worldService);
        advisedSupport.setTargetSource(targetSource);
        advisedSupport.addAdvisor(advisor);
    }

    @Test
    public void testJdkDynamicProxy() throws Exception {
        WorldService proxy = (WorldService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        // 通过代理调用 explode 方法
        proxy.explode();

        System.out.println("===== SUCCESS =====");
    }

    @Test
    public void testCglibDynamicProxy() throws Exception {
        WorldService proxy = (WorldService) new CglibAopProxy(advisedSupport).getProxy();
        proxy.explode();

        System.out.println("===== SUCCESS =====");
    }

    @Test
    public void testProxyFactory() throws Exception {
        // 使用 JDK 动态代理
        ProxyFactory factory = (ProxyFactory) advisedSupport;
        factory.setProxyTargetClass(false);
        WorldService proxy = (WorldService) factory.getProxy();
        proxy.explode();

        // 使用 CGLIB 动态代理
        factory.setProxyTargetClass(true);
        proxy = (WorldService) factory.getProxy();
        proxy.explode();

        System.out.println("===== SUCCESS =====");
    }

    @Test
    public void testBeforeAdvice() throws Exception {
        // 设置 BeforeAdvice
        String expression = "execution(* com.chen.myspring.service.WorldService.explode(..))";
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression(expression);
        MethodBeforeAdviceInterceptor methodInterceptor = new MethodBeforeAdviceInterceptor(new WorldServiceBeforeAdvice());
        advisor.setAdvice(methodInterceptor);
        advisedSupport.addAdvisor(advisor);
        ProxyFactory factory = (ProxyFactory) advisedSupport;

        WorldService proxy = (WorldService) factory.getProxy();
        proxy.explode();

        System.out.println("===== SUCCESS =====");
    }

    @Test
    public void testAdvisor() throws Exception {
        WorldService worldService = new WorldServiceImpl();

        // Advisor 是 Pointcut 和 Advice 的组合
        String expression = "execution(* com.chen.myspring.service.WorldService.explode(..))";
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression(expression);
        MethodBeforeAdviceInterceptor methodInterceptor = new MethodBeforeAdviceInterceptor(new WorldServiceBeforeAdvice());
        advisor.setAdvice(methodInterceptor);

        ClassFilter classFilter = advisor.getPointcut().getClassFilter();
        if (classFilter.matches(worldService.getClass())) {
            ProxyFactory proxyFactory = new ProxyFactory();

            TargetSource targetSource = new TargetSource(worldService);
            proxyFactory.setTargetSource(targetSource);
            proxyFactory.addAdvisor(advisor);
            // 使用 CGLIB
//			advisedSupport.setProxyTargetClass(true);

            WorldService proxy = (WorldService) proxyFactory.getProxy();
            proxy.explode();

            System.out.println("===== SUCCESS =====");
        }
    }
}
