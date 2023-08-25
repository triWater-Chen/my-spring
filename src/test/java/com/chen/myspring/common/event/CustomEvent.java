package com.chen.myspring.common.event;

import com.chen.myspring.context.ApplicationContext;
import com.chen.myspring.context.event.ApplicationContextEvent;

/**
 * @author Chen
 * @create 2023-08-18
 */
public class CustomEvent extends ApplicationContextEvent {

    /**
     * Constructs a prototypical Event.
     */
    public CustomEvent(ApplicationContext source) {
        super(source);
    }
}
