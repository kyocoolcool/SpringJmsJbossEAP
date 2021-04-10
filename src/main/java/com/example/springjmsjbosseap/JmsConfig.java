package com.example.springjmsjbosseap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.jndi.JndiTemplate;

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
    @Autowired
    JmsListener jmsListener;

    @Bean
    public JndiTemplate jndiTemplate() {
        JndiTemplate jndiTemplate = new JndiTemplate();
        Properties properties = new Properties();
        properties.setProperty("java.naming.factory.initial", "org.wildfly.naming.client.WildFlyInitialContextFactory");
//        properties.setProperty("java.naming.factory.url.pkgs", "javax.naming");
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
    public JmsTemplate jmsTemplate() throws NamingException {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(credentialsConnectionFactory());
        return jmsTemplate;
    }

    @Bean
    public ConnectionFactory connectionfactory() throws NamingException {
        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
        jndiObjectFactoryBean.setJndiTemplate(jndiTemplate());
        jndiObjectFactoryBean.setJndiName("jms/RemoteConnectionFactory");
        jndiObjectFactoryBean.afterPropertiesSet();
        return (ConnectionFactory)jndiObjectFactoryBean.getObject();
    }

    @Bean
    public UserCredentialsConnectionFactoryAdapter credentialsConnectionFactory() throws NamingException {
        UserCredentialsConnectionFactoryAdapter userCredentialsConnectionFactoryAdapter = new UserCredentialsConnectionFactoryAdapter();
        userCredentialsConnectionFactoryAdapter.setTargetConnectionFactory(connectionfactory());
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
    public SimpleMessageListenerContainer simpleMessageListenerContainer() throws NamingException {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionfactory());
        simpleMessageListenerContainer.setDestination(destination());
        simpleMessageListenerContainer.setMessageListener(jmsListener);
        return simpleMessageListenerContainer;
    }
}
