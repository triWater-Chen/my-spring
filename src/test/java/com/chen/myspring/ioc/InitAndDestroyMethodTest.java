package com.chen.myspring.ioc;

import com.chen.myspring.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author Chen
 * @description init-and-destroy-method
 *              bean 的初始化和销毁方法
 *              一：在 xml 文件中制定 init-method 和 destroy-method
 *              二：继承自 InitializingBean 和 DisposableBean
 * @create 2023-08-17
 */
public class InitAndDestroyMethodTest {

    @Test
    public void testInitAndDestroyMethod() throws Exception {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:init-and-destroy-method.xml");
//        applicationContext.registerShutdownHook();
        // 或者手动关闭
         applicationContext.close();

        System.out.println("===== SUCCESS =====");
    }
}
