package com.example.springjmsjbosseap;

import java.io.Serializable;

/**
 * @author 陳金昌 Chris Chen
 * @version 1.0 2021/4/15 6:01 PM
 */
public class Apple implements Serializable {
    private String name;

    public Apple(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "name='" + name + '\'' +
                '}';
    }
}
