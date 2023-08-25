package com.chen.myspring.ioc;

/**
 * @author Chen
 * @description bean-definition-and-bean-definition-registry
 *              最简单的 bean 容器
 *              BeanDefinition 和 BeanDefinitionRegistry
 *              Bean 实例化策略 InstantiationStrategy
 * @create 2023-08-15
 */
public class BeanDefinitionAndBeanDefinitionRegistryTest {

//    @Test
//    public void testBeanFactory() throws Exception {
//        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//        BeanDefinition beanDefinition = new BeanDefinition(HelloService.class);
//
//        // 使用 SimpleInstantiationStrategy
////        SimpleInstantiationStrategy simpleInstantiationStrategy = new SimpleInstantiationStrategy();
////        HelloService helloService = (HelloService) simpleInstantiationStrategy.instantiate(beanDefinition);
//
//        // 使用 AbstractAutowireCapableBeanFactory
//        beanFactory.registerBeanDefinition("helloService", beanDefinition);
//        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
//        helloService.sayHello();
//
//        System.out.println("===== SUCCESS =====");
//    }
}
