package com.chen.myspring.ioc;

import com.chen.myspring.beans.factory.support.DefaultListableBeanFactory;
import com.chen.myspring.beans.factory.xml.XmlBeanDefinitionReader;
import com.chen.myspring.bean.Car;
import com.chen.myspring.bean.Person;
import com.chen.myspring.common.CustomBeanFactoryPostProcessor;
import com.chen.myspring.common.CustomerBeanPostProcessor;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Chen
 * @description bean-factory-post-processor-and-bean-post-processor
 *              BeanFactoryPostProcessor 和 BeanPostProcessor
 *              手动添加
 * @create 2023-08-16
 */
public class BeanFactoryPostProcessorAndBeanPostProcessorTest {

    @Test
    public void testBeanFactoryPostProcessor() throws Exception {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        // 在所有 BeanDefinition 加载完成后，但在 bean 实例化之前，修改 BeanDefinition 的属性值
        CustomBeanFactoryPostProcessor beanFactoryPostProcessor = new CustomBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);
        // name 属性在 CustomBeanFactoryPostProcessor 中被修改为 Bob
        Assert.assertEquals(person.getName(), "Bob");

        System.out.println("===== SUCCESS =====");
    }

    @Test
    public void testBeanPostProcessor() throws Exception {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        // 添加 bean 实例化后的处理器
        CustomerBeanPostProcessor customerBeanPostProcessor = new CustomerBeanPostProcessor();
        beanFactory.addBeanPostProcessor(customerBeanPostProcessor);

        Car car = (Car) beanFactory.getBean("car");
        System.out.println(car);
        // brand 属性在 CustomerBeanPostProcessor 中被修改为 lamborghini
        Assert.assertEquals(car.getBrand(), "lamborghini");

        System.out.println("===== SUCCESS =====");
    }
}
