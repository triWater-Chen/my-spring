package com.chen.myspring.beans.others;

/**
 * @author Chen
 * @description 自定义异常
 * @create 2023-08-15
 */
public class BeansException extends RuntimeException{

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
