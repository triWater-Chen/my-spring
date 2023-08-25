package com.chen.myspring.common;

import com.chen.myspring.beans.factory.FactoryBean;
import com.chen.myspring.bean.Car;

/**
 * @author Chen
 * @create 2023-08-18
 */
public class CarFactoryBean implements FactoryBean<Car> {

    private String brand;

    @Override
    public Car getObject() throws Exception {
        Car car = new Car();
        car.setBrand(brand);
        return car;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
