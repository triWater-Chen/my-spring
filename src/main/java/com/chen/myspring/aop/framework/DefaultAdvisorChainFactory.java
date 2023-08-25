package com.chen.myspring.aop.framework;

import com.chen.myspring.aop.AdvisedSupport;
import com.chen.myspring.aop.Advisor;
import com.chen.myspring.aop.MethodMatcher;
import com.chen.myspring.aop.PointcutAdvisor;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chen
 * @create 2023-08-24
 */
public class DefaultAdvisorChainFactory implements AdvisorChainFactory {

    /**
     * 获取到所有与当前 method 匹配的 advice
     */
    @Override
    public List<Object> getInterceptorsAndDynamicInterceptionAdvice(AdvisedSupport config, Method method, Class<?> targetClass) {

        // 获取所有切面 advisor
        Advisor[] advisors = config.getAdvisors().toArray(new Advisor[0]);
        List<Object> interceptorList = new ArrayList<>(advisors.length);
        Class<?> actualClass = (targetClass != null ? targetClass : method.getDeclaringClass());
        for (Advisor advisor : advisors) {
            if (advisor instanceof PointcutAdvisor) {
                // Add it conditionally.
                PointcutAdvisor pointcutAdvisor = (PointcutAdvisor) advisor;

                // 校验当前 Advisor 是否适用于当前代理对象
                if (pointcutAdvisor.getPointcut().getClassFilter().matches(actualClass)) {

                    // 校验 Advisor 是否匹配当前调用的方法 method
                    MethodMatcher mm = pointcutAdvisor.getPointcut().getMethodMatcher();
                    boolean match;
                    match = mm.matches(method, actualClass);
                    if (match) {
                        MethodInterceptor interceptor = (MethodInterceptor) advisor.getAdvice();
                        interceptorList.add(interceptor);
                    }
                }
            }
        }
        return interceptorList;
    }
}
