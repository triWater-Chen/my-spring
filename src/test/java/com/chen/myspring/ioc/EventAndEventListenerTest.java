package com.chen.myspring.ioc;

import com.chen.myspring.context.support.ClassPathXmlApplicationContext;
import com.chen.myspring.common.event.CustomEvent;
import org.junit.Test;

/**
 * @author Chen
 * @description event-and-event-listener
 *              容器事件和事件监听器
 * @create 2023-08-18
 */
public class EventAndEventListenerTest {

    @Test
    public void testEventListener() throws Exception {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:event-and-event-listener.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext));

//        applicationContext.registerShutdownHook();
        // 或者主动关闭容器
         applicationContext.close();

        System.out.println("===== SUCCESS =====");
    }
}
