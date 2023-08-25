package com.chen.myspring.ioc;

import com.chen.myspring.bean.Car;
import com.chen.myspring.context.support.ClassPathXmlApplicationContext;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Chen
 * @description value-annotation
 *              @ Value 注解
 * @create 2023-08-22
 */
public class ValueAnnotationTest {

    @Test
    public void testValueAnnotation() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:value-annotation.xml");

        Car car = applicationContext.getBean("car", Car.class);
        Assert.assertEquals(car.getBrand(), "lamborghini");

        System.out.println("===== SUCCESS =====");
    }
}
