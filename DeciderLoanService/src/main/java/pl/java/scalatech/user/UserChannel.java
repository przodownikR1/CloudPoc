package pl.java.scalatech.user;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface UserChannel{
    @Output
    MessageChannel output();
}