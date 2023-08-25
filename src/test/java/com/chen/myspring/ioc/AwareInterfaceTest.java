package com.chen.myspring.ioc;

import com.chen.myspring.context.support.ClassPathXmlApplicationContext;
import com.chen.myspring.service.HelloService;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Chen
 * @description aware-interface
 *              Aware 接口
 *              在 bean 实例化后，执行 beanFactoryAware-setBeanFactory
 *              在 beanPostProcessor 前置处理中，执行 applicationContext-setApplicationContext
 * @create 2023-08-17
 */
public class AwareInterfaceTest {

    @Test
    public void test() throws Exception {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        HelloService helloService = applicationContext.getBean("helloService", HelloService.class);
        Assert.assertNotNull(helloService.getApplicationContext());
        Assert.assertNotNull(helloService.getBeanFactory());

        System.out.println("===== SUCCESS =====");
    }
}
