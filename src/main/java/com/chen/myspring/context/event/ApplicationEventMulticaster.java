package com.chen.myspring.context.event;

import com.chen.myspring.context.ApplicationEvent;
import com.chen.myspring.context.ApplicationListener;

/**
 * 注册监听器和发布事件的抽象接口
 */
public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);

    void multicastEvent(ApplicationEvent event);

}
