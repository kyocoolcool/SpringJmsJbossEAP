package com.example.springjmsjbosseap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.jndi.JndiTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.naming.NamingException;
import java.util.Properties;

/**
 * @author 陳金昌 Chris Chen
 * @version 1.0 2021/4/10 1:01 PM
 */
@Configuration
public class JmsConfig {
//    @Autowired
//    JmsListener jmsListener;

    @Bean
    public JndiTemplate jndiTemplate() {
        JndiTemplate jndiTemplate = new JndiTemplate();
        Properties properties = new Properties();
        properties.setProperty("java.naming.factory.initial", "org.wildfly.naming.client.WildFlyInitialContextFactory");
        //jboss eap7.3 不需要
        //properties.setProperty("java.naming.factory.url.pkgs", "javax.naming");
        properties.setProperty("java.naming.provider.url", "http-remoting://127.0.0.1:8080");
        properties.setProperty("java.naming.security.principal","chrischen");
        properties.setProperty("java.naming.security.credentials","Kyo77437743");
        jndiTemplate.setEnvironment(properties);
        return jndiTemplate;
    }

    @Bean
    public Destination destination() throws NamingException {
        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
        jndiObjectFactoryBean.setJndiTemplate(jndiTemplate());
        jndiObjectFactoryBean.setJndiName("jms/queue/test");
        jndiObjectFactoryBean.afterPropertiesSet();
        return (Destination)jndiObjectFactoryBean.getObject();
    }

    @Bean
    public Destination destination2() throws NamingException {
        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
        jndiObjectFactoryBean.setJndiTemplate(jndiTemplate());
        jndiObjectFactoryBean.setJndiName("jms/queue/test2");
        jndiObjectFactoryBean.afterPropertiesSet();
        return (Destination)jndiObjectFactoryBean.getObject();
    }
    @Bean
    public Destination destination3() throws NamingException {
        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
        jndiObjectFactoryBean.setJndiTemplate(jndiTemplate());
        jndiObjectFactoryBean.setJndiName("jms/queue/test3");
        jndiObjectFactoryBean.afterPropertiesSet();
        return (Destination)jndiObjectFactoryBean.getObject();
    }

    @Bean
    public Destination destination4() throws NamingException {
        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
        jndiObjectFactoryBean.setJndiTemplate(jndiTemplate());
        jndiObjectFactoryBean.setJndiName("jms/queue/test4");
        jndiObjectFactoryBean.afterPropertiesSet();
        return (Destination)jndiObjectFactoryBean.getObject();
    }

    @Bean
    public JmsTemplate jmsTemplate() throws NamingException {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(credentialsConnectionFactory());
        return jmsTemplate;
    }

    @Bean
    public ConnectionFactory jmsConnectionFactory() throws NamingException {
        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
        jndiObjectFactoryBean.setJndiTemplate(jndiTemplate());
        jndiObjectFactoryBean.setJndiName("jms/RemoteConnectionFactory");
        jndiObjectFactoryBean.afterPropertiesSet();
        return (ConnectionFactory)jndiObjectFactoryBean.getObject();
    }


    @Bean
    public UserCredentialsConnectionFactoryAdapter credentialsConnectionFactory() throws NamingException {
        UserCredentialsConnectionFactoryAdapter userCredentialsConnectionFactoryAdapter = new UserCredentialsConnectionFactoryAdapter();
        userCredentialsConnectionFactoryAdapter.setTargetConnectionFactory(jmsConnectionFactory());
        userCredentialsConnectionFactoryAdapter.setUsername("chrischen");
        userCredentialsConnectionFactoryAdapter.setPassword("Kyo77437743");
        return userCredentialsConnectionFactoryAdapter;
    }

    @Bean
    public JmsFactory jmsFactory() throws NamingException {
        JmsFactory jmsFactory = new JmsFactory();
        jmsFactory.setJmsTemplate(jmsTemplate());
        jmsFactory.setDestination(destination());
        return jmsFactory;
    }

    @Bean
    public JmsFactory jmsFactory2() throws NamingException {
        JmsFactory jmsFactory = new JmsFactory();
        jmsFactory.setJmsTemplate(jmsTemplate());
        jmsFactory.setDestination(destination2());
        return jmsFactory;
    }

    /*
     * @Description JmsListener configure
     * @param
     * @return org.springframework.jms.listener.SimpleMessageListenerContainer
     * @author 陳金昌 Chris Chen
     * @since 2021/4/16 4:27 PM
     */
//    @Bean
//    public SimpleMessageListenerContainer simpleMessageListenerContainer() throws NamingException {
//        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
//        simpleMessageListenerContainer.setConnectionFactory(jmsConnectionFactory());
//        simpleMessageListenerContainer.setDestination(destination2());
//        simpleMessageListenerContainer.setMessageListener(jmsListener);
//        return simpleMessageListenerContainer;
//    }

    @Service
    public class SomeHandler implements ErrorHandler {

        @Override
        public void handleError(Throwable t) {
            System.out.println("Error in listener: "+ t);
        }
    }

}
