package com.chen.myspring.common.event;

import com.chen.myspring.context.ApplicationListener;
import com.chen.myspring.context.event.ContextClosedEvent;

/**
 * @author Chen
 * @create 2023-08-18
 */
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println(this.getClass().getName());
    }
}
