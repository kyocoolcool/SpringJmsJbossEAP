package com.example.springjmsjbosseap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@SpringBootTest
class SpringJmsJbossEapApplicationTests {

    @Autowired
    public JmsFactory jmsFactory;

    @Test
    void contextLoads() {
        jmsFactory.getJmsTemplate().send(jmsFactory.getDestination(), new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("Spring Integration JMS");
            }
        });
    }

}
