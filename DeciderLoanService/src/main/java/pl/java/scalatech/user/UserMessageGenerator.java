package pl.java.scalatech.user;

import static org.springframework.cloud.stream.messaging.Source.OUTPUT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.generator.Generator;
import pl.java.scalatech.user.domain.User;

@Component
@Slf4j
public class UserMessageGenerator {

    @Autowired
    @Output(OUTPUT)
    private MessageChannel output;
    
    private Generator generator;
    @Autowired(required = false)
    private UserWriter userWriter;

    public UserMessageGenerator(Generator generator) {

        this.generator = generator;
        // this.userWriter = userWriter;
    }

    @Scheduled(fixedDelay = 10000)
    public void replenishUserStore() {
        Message<User> msg = MessageBuilder.withPayload(generator.generateUser()).build();
        log.info("send === > {}", msg.getPayload());
        this.output.send(msg);
    }

    // @Scheduled(fixedDelay=15000)
    public void replenishUserStoreWriter() {
        User newUser = generator.generateUser();
        userWriter.writeUser(newUser);
        log.info("send writer=== > {}", newUser);

    }
}
