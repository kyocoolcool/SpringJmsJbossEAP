package com.example.springjmsjbosseap;

import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;

/**
 * @author 陳金昌 Chris Chen
 * @version 1.0 2021/4/11 2:13 AM
 */
public class JmsFactory {
    private JmsTemplate jmsTemplate;
    private Destination destination;

    public JmsFactory() {
    }

    public JmsFactory(JmsTemplate jmsTemplate, Destination destination) {
        this.jmsTemplate = jmsTemplate;
        this.destination = destination;
    }

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }
}
