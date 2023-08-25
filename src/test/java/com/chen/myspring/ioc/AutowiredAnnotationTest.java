package com.chen.myspring.ioc;

import com.chen.myspring.bean.Person;
import com.chen.myspring.context.support.ClassPathXmlApplicationContext;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Chen
 * @description autowired-annotation
 *              @ Autowired 注解
 * @create 2023-08-22
 */
public class AutowiredAnnotationTest {

    @Test
    public void testAutowiredAnnotation() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:autowired-annotation.xml");

        Person person = applicationContext.getBean(Person.class);
        Assert.assertNotNull(person.getCar());

        System.out.println("===== SUCCESS =====");
    }
}
