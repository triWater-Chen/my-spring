package com.chen.myspring.ioc;

import com.chen.myspring.bean.A;
import com.chen.myspring.bean.B;
import com.chen.myspring.context.support.ClassPathXmlApplicationContext;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Chen
 * @description circular-reference-without-proxy-bean
 *              使用二级缓存来解决循环依赖
 *              不能解决有代理对象时的循环依赖
 * @create 2023-08-23
 */
public class CircularReferenceWithoutProxyBeanTest {

    @Test
    public void testCircularReference() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:circular-reference-without-proxy-bean.xml");
        A a = applicationContext.getBean("a", A.class);
        B b = applicationContext.getBean("b", B.class);
        Assert.assertSame(a.getB(), b);

        System.out.println("===== SUCCESS =====");
    }
}
