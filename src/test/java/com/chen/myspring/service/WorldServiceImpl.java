package com.chen.myspring.service;

/**
 * @author Chen
 * @create 2023-08-21
 */
public class WorldServiceImpl implements WorldService{

    private String name;

    @Override
    public void explode() {
        System.out.println("The Earth is going to explode");
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
