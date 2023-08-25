package com.chen.myspring.common.event;

import com.chen.myspring.context.ApplicationListener;
import com.chen.myspring.context.event.ContextRefreshedEvent;

/**
 * @author Chen
 * @create 2023-08-18
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println(this.getClass().getName());
    }
}
