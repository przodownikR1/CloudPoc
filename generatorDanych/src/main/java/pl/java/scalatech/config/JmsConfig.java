package pl.java.scalatech.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("jms")
public class JmsConfig {
    @Bean
    ActiveMQConnectionFactory amqFactory(){
        return new ActiveMQConnectionFactory("tcp://localhost:61616"); 
    }
   
   
}
