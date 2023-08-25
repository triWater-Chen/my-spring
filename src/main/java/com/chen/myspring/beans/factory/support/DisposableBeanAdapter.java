package com.chen.myspring.beans.factory.support;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.chen.myspring.beans.factory.DisposableBean;
import com.chen.myspring.beans.factory.config.BeanDefinition;
import com.chen.myspring.beans.others.BeansException;

import java.lang.reflect.Method;

/**
 * @author Chen
 * @create 2023-08-17
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;

    private final String beanName;

    private final String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {

        // 执行继承自 DisposableBean 接口所重写的销毁方法
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }

        // 避免同时继承自 DisposableBean，且自定义方法与 DisposableBean 方法同名，销毁方法执行两次的情况
        if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {
            // 执行自定义销毁方法
            Method destroyMethod = ClassUtil.getPublicMethod(bean.getClass(), destroyMethodName);
            if (destroyMethod == null) {
                throw new BeansException("Couldn't find a destroy method named '" + destroyMethodName + "' on bean with name '" + beanName + "'");
            }
            destroyMethod.invoke(bean);
        }
    }
}
