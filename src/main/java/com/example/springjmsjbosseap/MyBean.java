package com.example.springjmsjbosseap;

import org.springframework.messaging.handler.annotation.Header;

import java.util.Optional;

/**
 * @author 陳金昌 Chris Chen
 * @version 1.0 2021/4/15 11:39 PM
 */
public class MyBean {
    public String computeValue(Optional<Apple> payload,
                               @Header(value = "foo", required = false) String foo1,
                               @Header(value = "foo") Optional<String> foo2) {
        if (payload.isPresent()) {
            Apple value = payload.get();
            System.out.println(value);
            return value.getName();
        } else {
            System.out.println("empty");
        }
        return "no";
    }
}
