package pl.java.scalatech.user;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.user.domain.User;

@RestController
@Slf4j
@RequestMapping("/api/user/channel")
public class UserChannelController {

    
    private MessageChannel output;
    
     public UserChannelController(UserChannel channel) {
         this.output= channel.output();
        
    }
    
    @RequestMapping(method=RequestMethod.POST,value="/")
    void save(@RequestBody User user){
        Message<User> msg = MessageBuilder.withPayload(user).build();
        this.output.send(msg);
    }
}
