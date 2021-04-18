package com.example.springjmsjbosseap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@SpringBootTest
class SpringJmsJbossEapApplicationTests {
    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    public JmsFactory jmsFactory;

    @Autowired
    public JmsFactory jmsFactory2;

//    @Autowired
//    Greetings greetings;


    @Test
    void send() {
//        for (int i = 0; i < 20; i++) {
            jmsFactory.getJmsTemplate().send(jmsFactory.getDestination(), new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    //傳送Object
//                    return session.createObjectMessage(new Apple("OK"));
                    //傳送String
                    return session.createTextMessage("Spring Integration JMS");
                }
            });
//        }
    }

    @Test
    public void getData() throws JMSException {
        for (int i = 0; i < 50; i++) {
            Message receive = jmsFactory.getJmsTemplate().receive(jmsFactory2.getDestination());
            System.out.println(receive);
        }
    }

    @Test
    void sendAndReceive() throws JMSException {
        Message message = jmsFactory.getJmsTemplate().sendAndReceive(jmsFactory.getDestination(), new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(new Apple("good"));
            }
        });
        System.out.println(message.getBody(String.class));
//        System.out.println(message.getBody(String.class));
    }

    @Test
    void test2() {
//        greetings.send("Nick");
    }


    @Test
    public void testIntegrationGateway() {
        QueueChannel demoChannel =
                applicationContext.getBean("demoChannel2",
                        QueueChannel.class);
        System.out.println("request: "+demoChannel.receive().getPayload());
//        demoChannel.send(new MessageCreator());

//        QueueChannel demoChannel2 =
//                applicationContext.getBean("demoChannel2",
//                        QueueChannel.class);
//        System.out.println("reply: "+demoChannel2.receive().getPayload());
    }
}
