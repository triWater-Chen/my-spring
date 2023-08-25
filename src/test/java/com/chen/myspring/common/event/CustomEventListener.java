package com.chen.myspring.common.event;

import com.chen.myspring.context.ApplicationListener;

/**
 * @author Chen
 * @create 2023-08-18
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {

    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println(this.getClass().getName());
    }
}
