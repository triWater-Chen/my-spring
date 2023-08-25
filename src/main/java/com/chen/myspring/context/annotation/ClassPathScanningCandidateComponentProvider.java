package com.chen.myspring.context.annotation;

import cn.hutool.core.util.ClassUtil;
import com.chen.myspring.beans.factory.config.BeanDefinition;
import com.chen.myspring.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Chen
 * @create 2023-08-22
 */
public class ClassPathScanningCandidateComponentProvider {

    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<BeanDefinition>();
        // 扫描有 org.springframework.stereotype.Component 注解的类
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes) {
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            candidates.add(beanDefinition);
        }
        return candidates;
    }
}
