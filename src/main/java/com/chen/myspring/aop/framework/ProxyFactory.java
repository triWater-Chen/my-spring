package com.chen.myspring.aop.framework;

import com.chen.myspring.aop.AdvisedSupport;

/**
 * @author Chen
 * @description AOP 代理工厂
 * @create 2023-08-21
 */
public class ProxyFactory extends AdvisedSupport {

    public ProxyFactory() {
    }

    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy() {
        if (this.isProxyTargetClass() || this.getTargetSource().getTargetClass().length == 0) {
            return new CglibAopProxy(this);
        }

        return new JdkDynamicAopProxy(this);
    }
}
