package com.chen.myspring.ioc;

import com.chen.myspring.context.support.ClassPathXmlApplicationContext;
import com.chen.myspring.bean.Car;
import com.chen.myspring.bean.Person;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Chen
 * @description application-context
 *              应用上下文 ApplicationContext
 *              实现从 xml 中自动加载
 * @create 2023-08-17
 */
public class ApplicationContextTest {

    @Test
    public void testApplicationContext() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");

        Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person);
        // name 属性在 CustomBeanFactoryPostProcessor 中被修改为 Bob
        Assert.assertEquals(person.getName(), "Bob");

        Car car = applicationContext.getBean("car", Car.class);
        System.out.println(car);
        // brand 属性在 CustomerBeanPostProcessor 中被修改为 lamborghini
        Assert.assertEquals(car.getBrand(), "lamborghini");

        System.out.println("===== SUCCESS =====");
    }

}
