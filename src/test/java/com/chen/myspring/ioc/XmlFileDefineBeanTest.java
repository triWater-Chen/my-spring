package com.chen.myspring.ioc;

import com.chen.myspring.beans.factory.support.DefaultListableBeanFactory;
import com.chen.myspring.beans.factory.xml.XmlBeanDefinitionReader;
import com.chen.myspring.bean.Car;
import com.chen.myspring.bean.Person;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Chen
 * @description xml-file-define-bean
 *              在 xml 文件中定义 bean
 * @create 2023-08-16
 */
public class XmlFileDefineBeanTest {

    @Test
    public void testXmlFile() throws Exception {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);
        Assert.assertEquals(person.getName(), "Alice");
        Assert.assertEquals(person.getCar().getBrand(), "Bentley");

        Car car = (Car) beanFactory.getBean("car");
        System.out.println(car);
        Assert.assertEquals(car.getBrand(), "Bentley");

        System.out.println("===== SUCCESS =====");
    }
}
