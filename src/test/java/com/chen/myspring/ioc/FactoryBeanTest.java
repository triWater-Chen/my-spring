package com.chen.myspring.ioc;

import com.chen.myspring.context.support.ClassPathXmlApplicationContext;
import com.chen.myspring.bean.Car;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Chen
 * @description factory-bean
 *              FactoryBean，用于自定义 bean 的创建逻辑
 * @create 2023-08-18
 */
public class FactoryBeanTest {

    @Test
    public void testFactoryBean() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:factory-bean.xml");

        Car car = applicationContext.getBean("car", Car.class);
        Assert.assertEquals(car.getBrand(), "Bentley");

        System.out.println("===== SUCCESS =====");
    }
}
