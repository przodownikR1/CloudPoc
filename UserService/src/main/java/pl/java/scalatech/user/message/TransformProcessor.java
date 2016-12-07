package pl.java.scalatech.user.message;

import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Transformer;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.user.domain.User;

@Slf4j
//@Component
public class TransformProcessor {
  @Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
  public User transform(User user) {
     user.setLogin("XXX-"+user.getLogin()+"-XXX");
     log.info("Processor : {}",user);
     return user;
  }
}