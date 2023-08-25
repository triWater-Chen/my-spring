package com.chen.myspring.beans.factory;

import com.chen.myspring.beans.others.BeansException;

public interface ObjectFactory<T> {

    T getObject() throws BeansException;
}
