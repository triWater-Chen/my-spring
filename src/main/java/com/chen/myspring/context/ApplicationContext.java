package com.chen.myspring.context;

import com.chen.myspring.beans.factory.HierarchicalBeanFactory;
import com.chen.myspring.beans.factory.ListableBeanFactory;
import com.chen.myspring.core.io.ResourceLoader;

/**
 * 应用上下文
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
