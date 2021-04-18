package com.example.springjmsjbosseap;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author 陳金昌 Chris Chen
 * @version 1.0 2021/4/11 2:25 AM
 */
//@Component
public class JmsListener implements MessageListener {
//    @Autowired
//    private JmsTemplate jmsTemplate;

    /*
     * @description 取Queue實作方法,此範例包含回傳值
     * @param message
     * @return void
     * @author 陳金昌 Chris Chen
     * @since 2021/4/16 4:17 PM
     */
//    @Override
//    public void onMessage(Message message) {
//        ObjectMessage objectMessage = (ObjectMessage)message;
//        try {
//            System.out.println("accept message:"+ objectMessage.getObject());
//            jmsTemplate.send(message.getJMSReplyTo(), new MessageCreator() {
//
//                @Override
//                public Message createMessage(Session session) throws JMSException {
//                    Message responseMsg = session.createTextMessage("bar");
//                    responseMsg.setJMSCorrelationID(message.getJMSCorrelationID());
//                    return responseMsg;
//                }
//            });
//        } catch (JMSException e) {
//            e.printStackTrace();
//        }
//    }

    /*
     * @description 取Queue實作方法,此範例沒有回傳值
     * @param message
     * @return void
     * @author 陳金昌 Chris Chen
     * @since 2021/4/16 4:17 PM
     */
    @Override
    public void onMessage(Message message) {
        TextMessage objectMessage = (TextMessage)message;
        try {
            System.out.println("accept message:"+ objectMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
