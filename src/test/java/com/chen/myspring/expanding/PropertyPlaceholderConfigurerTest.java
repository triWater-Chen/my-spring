package com.chen.myspring.expanding;

import com.chen.myspring.bean.Car;
import com.chen.myspring.context.support.ClassPathXmlApplicationContext;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Chen
 * @description property-placeholder-configurer
 *              PropertyPlaceholderConfigurer
 * @create 2023-08-22
 */
public class PropertyPlaceholderConfigurerTest {

    @Test
    public void test() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:property-placeholder-configurer.xml");

        Car car = applicationContext.getBean("car", Car.class);
        Assert.assertEquals(car.getBrand(), "lamborghini");

        System.out.println("===== SUCCESS =====");
    }
}
