package pl.java.scalatech.user;

import org.springframework.messaging.MessageChannel;

public class UserMessageGenerator {

   
    private final MessageChannel output;
    
    public UserMessageGenerator(UserChannel channel) {
        this.output= channel.output();
       
   }
    
    
   
}
