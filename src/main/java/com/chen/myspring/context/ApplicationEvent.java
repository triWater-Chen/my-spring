package com.chen.myspring.context;

import java.util.EventObject;

/**
 * @author Chen
 * @create 2023-08-18
 */
public abstract class ApplicationEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
