package com.chen.myspring.ioc;

import com.chen.myspring.beans.factory.config.BeanDefinition;
import com.chen.myspring.beans.factory.config.BeanReference;
import com.chen.myspring.beans.factory.support.DefaultListableBeanFactory;
import com.chen.myspring.bean.Car;
import com.chen.myspring.bean.Person;
import com.chen.myspring.beans.others.PropertyValue;
import com.chen.myspring.beans.others.PropertyValues;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Chen
 * @description populate-bean-with-property-values
 *              为 bean 填充属性
 *              为 bean 注入 bean
 * @create 2023-08-15
 */
public class PopulateBeanWithPropertyValuesTest {

    @Test
    public void testPopulateBeanWithPropertyValues() throws Exception {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name", "Alice"));
        propertyValues.addPropertyValue(new PropertyValue("age", 18));
        BeanDefinition beanDefinition = new BeanDefinition(Person.class, propertyValues);
        beanFactory.registerBeanDefinition("person", beanDefinition);

        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);
        Assert.assertEquals(person.getName(), "Alice");
        Assert.assertEquals(person.getAge(), 18);

        System.out.println("===== SUCCESS =====");
    }

    /**
     * 为 bean 注入 bean
     */
    @Test
    public void testPopulateBeanWithBean() throws Exception {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 注册 Car 实例
        PropertyValues propertyValuesForCar = new PropertyValues();
        propertyValuesForCar.addPropertyValue(new PropertyValue("brand", "Bentley"));
        BeanDefinition carBeanDefinition = new BeanDefinition(Car.class, propertyValuesForCar);
        beanFactory.registerBeanDefinition("car", carBeanDefinition);

        // 注册 Person 实例
        PropertyValues propertyValuesForPerson = new PropertyValues();
        propertyValuesForPerson.addPropertyValue(new PropertyValue("name", "Alice"));
        propertyValuesForPerson.addPropertyValue(new PropertyValue("age", 18));

        // Person 实例依赖 Car 实例
        propertyValuesForPerson.addPropertyValue(new PropertyValue("car", new BeanReference("car")));
        BeanDefinition beanDefinition = new BeanDefinition(Person.class, propertyValuesForPerson);
        beanFactory.registerBeanDefinition("person", beanDefinition);

        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);
        Assert.assertEquals(person.getName(), "Alice");
        Assert.assertEquals(person.getAge(), 18);
        Car car = person.getCar();
        Assert.assertNotNull(car);
        Assert.assertEquals(car.getBrand(), "Bentley");

        System.out.println("===== SUCCESS =====");
    }
}
