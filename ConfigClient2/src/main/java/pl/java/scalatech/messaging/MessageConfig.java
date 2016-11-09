package pl.java.scalatech.messaging;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

@Configuration
public class MessageConfig {
    @Bean
    public MessageChannel requestGetProcessorChannel() {
        DirectChannel channel = new DirectChannel();
        return channel;
    }


    @MessageEndpoint
    public static class GetRequestEcho {
        @ServiceActivator(inputChannel = "requestGetProcessorChannel")
        public Message<?> echo(Message<?> message) {
            return MessageBuilder.withPayload("Server is Running. . .").build();
        }
    }

    @Bean
    public MessageChannel requestChannel() {
        DirectChannel channel = new DirectChannel();
        return channel;
    }
}
