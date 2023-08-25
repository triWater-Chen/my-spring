package com.chen.myspring.ioc;

import com.chen.myspring.bean.Car;
import com.chen.myspring.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author Chen
 * @description lazy-init-and-multi-advice
 *              懒加载
 * @create 2023-08-24
 */
public class LazyInitTest {

    @Test
    public void testLazyInit() throws InterruptedException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:lazy-test.xml");
        System.out.println(System.currentTimeMillis() + ":applicationContext-over");

        TimeUnit.SECONDS.sleep(1);
        Car c = (Car) applicationContext.getBean("car");
        // 显示 bean 的创建时间
        c.showTime();

        System.out.println("===== SUCCESS =====");
    }
}
