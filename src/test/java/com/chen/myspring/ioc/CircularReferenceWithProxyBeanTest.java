package com.chen.myspring.ioc;

import com.chen.myspring.bean.A;
import com.chen.myspring.bean.B;
import com.chen.myspring.context.support.ClassPathXmlApplicationContext;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Chen
 * @description circular-reference-with-proxy-bean
 *              三级缓存解决循环代理
 * @create 2023-08-23
 */
public class CircularReferenceWithProxyBeanTest {

    @Test
    public void testCircularReference() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:circular-reference-with-proxy-bean.xml");
        A a = applicationContext.getBean("a", A.class);
        B b = applicationContext.getBean("b", B.class);
        a.func();
        Assert.assertSame(b.getA(), a);

        System.out.println("===== SUCCESS =====");
    }
}
