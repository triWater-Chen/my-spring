package com.chen.myspring.aop;

import com.chen.myspring.context.support.ClassPathXmlApplicationContext;
import com.chen.myspring.service.WorldService;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Chen
 * @description auto-proxy：动态代理融入 bean 生命周期
 *              populate-proxy-bean-with-property-values：实现为代理 bean 设置属性
 * @create 2023-08-21
 */
public class AutoProxyTest {

    /**
     * 最终完整版
     */
    @Test
    public void testAutoProxy() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:auto-proxy.xml");

        // 获取代理对象
        WorldService worldService = applicationContext.getBean("worldService", WorldService.class);
        worldService.explode();

        System.out.println("===== SUCCESS =====");
    }

    @Test
    public void testPopulateProxyBeanWithPropertyValues() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:populate-proxy-bean-with-property-values.xml");

        // 获取代理对象
        WorldService worldService = applicationContext.getBean("worldService", WorldService.class);
        worldService.explode();
        Assert.assertEquals(worldService.getName(), "earth");

        System.out.println("===== SUCCESS =====");
    }
}
