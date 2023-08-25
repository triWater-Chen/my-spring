package com.chen.myspring.beans.others;

/**
 * @author Chen
 * @description bean 属性信息
 * @create 2023-08-15
 */
public class PropertyValue {

    private final String name;

    private final Object value;

    public PropertyValue (String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
