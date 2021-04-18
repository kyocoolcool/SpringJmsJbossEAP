package com.example.springjmsjbosseap;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

/**
 * @author 陳金昌 Chris Chen
 * @version 1.0 2021/4/17 12:18 PM
 */
@MessageEndpoint
public class DemoBean {

    @ServiceActivator
    public String upperCase(String input) {
        System.out.println(input);
        return "JMS response: " + input.toUpperCase();
    }
}
