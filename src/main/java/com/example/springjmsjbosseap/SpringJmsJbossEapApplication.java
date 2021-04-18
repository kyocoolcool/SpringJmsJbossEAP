package com.example.springjmsjbosseap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:jms-gateway.xml")
public class SpringJmsJbossEapApplication {

    public static void main(String[] args) {
         SpringApplication.run(SpringJmsJbossEapApplication.class, args);
    }

}
