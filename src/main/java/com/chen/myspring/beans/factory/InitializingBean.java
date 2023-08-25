package com.chen.myspring.beans.factory;

public interface InitializingBean {

    void afterPropertiesSet() throws Exception;
}
