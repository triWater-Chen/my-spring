package com.chen.myspring.context.annotation;

import cn.hutool.core.util.StrUtil;
import com.chen.myspring.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.chen.myspring.beans.factory.config.BeanDefinition;
import com.chen.myspring.beans.factory.support.BeanDefinitionRegistry;
import com.chen.myspring.stereotype.Component;

import java.util.Set;

/**
 * @author Chen
 * @create 2023-08-22
 */
public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider {

    public static final String AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME = "com.chen.myspring.context.annotation.internalAutowiredAnnotationProcessor";

    private BeanDefinitionRegistry registry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void doScan(String... basePackages) {
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for (BeanDefinition candidate : candidates) {
                // 解析 bean 的作用域
                String beanScope = resolveBeanScope(candidate);
                if (StrUtil.isNotEmpty(beanScope)) {
                    candidate.setScope(beanScope);
                }
                // 生成 bean 的名称
                String beanName = determineBeanName(candidate);
                // 注册 BeanDefinition
                registry.registerBeanDefinition(beanName, candidate);
            }
        }

        // 注册处理 @Autowired 和 @Value 注解的 BeanPostProcessor
        registry.registerBeanDefinition(AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME, new BeanDefinition(AutowiredAnnotationBeanPostProcessor.class));
    }

    /**
     * 获取 bean 的作用域
     */
    private String resolveBeanScope(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Scope scope = beanClass.getAnnotation(Scope.class);
        if (scope != null) {
            return scope.value();
        }

        return StrUtil.EMPTY;
    }


    /**
     * 生成 bean 的名称
     */
    private String determineBeanName(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Component component = beanClass.getAnnotation(Component.class);
        String value = component.value();
        if (StrUtil.isEmpty(value)) {
            value = StrUtil.lowerFirst(beanClass.getSimpleName());
        }
        return value;
    }

}
