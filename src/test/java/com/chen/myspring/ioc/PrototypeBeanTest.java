package com.chen.myspring.ioc;

import com.chen.myspring.context.support.ClassPathXmlApplicationContext;
import com.chen.myspring.bean.Car;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Chen
 * @description prototype-bean
 *              bean 作用域，增加 prototype 的支持
 * @create 2023-08-18
 */
public class PrototypeBeanTest {

    @Test
    public void testPrototype() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:prototype-bean.xml");

        Car car1 = applicationContext.getBean("car", Car.class);
        Car car2 = applicationContext.getBean("car", Car.class);
        Assert.assertNotSame(car1, car2);

        System.out.println("===== SUCCESS =====");
    }
}
