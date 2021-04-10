package com.example.springjmsjbosseap;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author 陳金昌 Chris Chen
 * @version 1.0 2021/4/11 2:25 AM
 */
@Component
public class JmsListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        TextMessage text = (TextMessage)message;
        try {
            System.out.println("accept message:"+ text.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
