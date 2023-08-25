package com.chen.myspring.ioc;

import com.chen.myspring.bean.Car;
import com.chen.myspring.context.support.ClassPathXmlApplicationContext;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Chen
 * @description package-scan
 *              包扫描
 * @create 2023-08-22
 */
public class PackageScanTest {

    @Test
    public void testScanPackage() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:package-scan.xml");

        Car car = applicationContext.getBean("car", Car.class);
        Assert.assertNotNull(car);

        System.out.println("===== SUCCESS =====");
    }
}
