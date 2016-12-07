package pl.java.scalatech.user.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface UserChannel {

    @Input
    SubscribableChannel input();
}
