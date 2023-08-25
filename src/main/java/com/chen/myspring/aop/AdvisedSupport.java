package com.chen.myspring.aop;

import com.chen.myspring.aop.framework.AdvisorChainFactory;
import com.chen.myspring.aop.framework.DefaultAdvisorChainFactory;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Chen
 * @description AOP 代理配置管理器的基类
 * @create 2023-08-21
 */
public class AdvisedSupport {

    // true 则使用 cglib 代理
    private boolean proxyTargetClass = false;

    private TargetSource targetSource;

//    private MethodInterceptor methodInterceptor;

    private MethodMatcher methodMatcher;

    private transient Map<Integer, List<Object>> methodCache;

    AdvisorChainFactory advisorChainFactory = new DefaultAdvisorChainFactory();

    private List<Advisor> advisors = new ArrayList<>();

    public AdvisedSupport() {
        this.methodCache = new ConcurrentHashMap<>(32);
    }

    public boolean isProxyTargetClass() {
        return proxyTargetClass;
    }

    public void setProxyTargetClass(boolean proxyTargetClass) {
        this.proxyTargetClass = proxyTargetClass;
    }

    public void addAdvisor(Advisor advisor) {
        advisors.add(advisor);
    }

    public List<Advisor> getAdvisors() {
        return advisors;
    }

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

//    public MethodInterceptor getMethodInterceptor() {
//        return methodInterceptor;
//    }
//
//    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
//        this.methodInterceptor = methodInterceptor;
//    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }

    /**
     * 用来返回方法的拦截器链
     */
    public List<Object> getInterceptorsAndDynamicInterceptionAdvice(Method method, Class<?> targetClass) {
        Integer cacheKey = method.hashCode();
        List<Object> cached = this.methodCache.get(cacheKey);
        if (cached == null) {
            cached = this.advisorChainFactory.getInterceptorsAndDynamicInterceptionAdvice(this, method, targetClass);
            // 将获取过的拦截器放入缓存
            this.methodCache.put(cacheKey, cached);
        }
        return cached;
    }
}
