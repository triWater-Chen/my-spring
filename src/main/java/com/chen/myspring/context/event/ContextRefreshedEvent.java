package com.chen.myspring.context.event;

import com.chen.myspring.context.ApplicationContext;

/**
 * @author Chen
 * @create 2023-08-18
 */
public class ContextRefreshedEvent extends ApplicationContextEvent {

    /**
     * Constructs a prototypical Event.
     */
    public ContextRefreshedEvent(ApplicationContext source) {
        super(source);
    }
}
