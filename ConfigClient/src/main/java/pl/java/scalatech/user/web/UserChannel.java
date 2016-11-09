package pl.java.scalatech.user.web;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface UserChannel{
    @Output
    MessageChannel output();
}