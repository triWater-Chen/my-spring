package com.chen.myspring.aop.framework.autoproxy;

import com.chen.myspring.aop.*;
import com.chen.myspring.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.chen.myspring.aop.framework.ProxyFactory;
import com.chen.myspring.beans.factory.BeanFactory;
import com.chen.myspring.beans.factory.BeanFactoryAware;
import com.chen.myspring.beans.factory.config.BeanDefinition;
import com.chen.myspring.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.chen.myspring.beans.factory.support.DefaultListableBeanFactory;
import com.chen.myspring.beans.others.BeansException;
import com.chen.myspring.beans.others.PropertyValues;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Chen
 * @description 处理横切逻辑的织入返回代理对象的 InstantiationAwareBeanPostProcessor 实现类，当对象实例化时，生成代理对象并返回
 * @create 2023-08-21
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    private Set<Object> earlyProxyReferences = new HashSet<>();

    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
        earlyProxyReferences.add(beanName);
        return wrapIfNecessary(bean, beanName);
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!earlyProxyReferences.contains(beanName)) {
            return wrapIfNecessary(bean, beanName);
        }

        return bean;
    }

    protected Object wrapIfNecessary(Object bean, String beanName) {
        // 避免死循环
        if (isInfrastructureClass(bean.getClass())) {
            return bean;
        }

        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();
        try {
            ProxyFactory proxyFactory = new ProxyFactory();
            for (AspectJExpressionPointcutAdvisor advisor : advisors) {
                ClassFilter classFilter = advisor.getPointcut().getClassFilter();
                if (classFilter.matches(bean.getClass())) {
                    TargetSource targetSource = new TargetSource(bean);
                    proxyFactory.setTargetSource(targetSource);
                    proxyFactory.addAdvisor(advisor);
                    proxyFactory.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
                }
            }

            // 返回代理对象
            if (!proxyFactory.getAdvisors().isEmpty()) {
                return proxyFactory.getProxy();
            }
        } catch (Exception ex) {
            throw new BeansException("Error create proxy bean for: " + beanName, ex);
        }
        return bean;
    }

    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass)
                || Pointcut.class.isAssignableFrom(beanClass)
                || Advisor.class.isAssignableFrom(beanClass);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        return pvs;
    }
}
