package pl.java.scalatech.user.message;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

import pl.java.scalatech.user.domain.User;
import pl.java.scalatech.user.repo.UserRepo;

@MessageEndpoint
public class UserProcessor {
    
  private final UserRepo userRepo;
  
  public UserProcessor(UserRepo userRepo) {
    this.userRepo = userRepo;
}
    
@ServiceActivator(inputChannel="input")    
public void onNew(Message<User> msg){
    userRepo.save(msg.getPayload());
}
}
