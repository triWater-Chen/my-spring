package com.chen.myspring.context.event;

import com.chen.myspring.context.ApplicationContext;
import com.chen.myspring.context.ApplicationEvent;

/**
 * @author Chen
 * @create 2023-08-18
 */
public abstract class ApplicationContextEvent extends ApplicationEvent {

    /**
     * Constructs a prototypical Event.
     */
    public ApplicationContextEvent(ApplicationContext source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
