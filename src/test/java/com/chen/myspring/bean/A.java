package com.chen.myspring.bean;

/**
 * @author Chen
 * @create 2023-08-23
 */
public class A {

    private B b;

    public void func(){
        System.out.println("A");
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }
}
