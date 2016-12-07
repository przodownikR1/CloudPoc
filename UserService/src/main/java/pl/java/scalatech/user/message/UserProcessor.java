package pl.java.scalatech.user.message;

import static org.springframework.cloud.stream.messaging.Sink.INPUT;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.annotation.Timed;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.user.domain.User;
import pl.java.scalatech.user.repo.UserRepo;

@MessageEndpoint
@Slf4j
public class UserProcessor {

    private final UserRepo userRepo;

    public UserProcessor(UserRepo userRepo, MetricRegistry metricRegistry) {
        this.userRepo = userRepo;
        metricRegistry.register("onNew", (Gauge<Integer>) () -> 1);
    }
    //@StreamListener
    @ServiceActivator(inputChannel = INPUT)
    @Timed(name = "onNewUserTimed")
    public void onNew(Message<User> msg) {
        log.info("received <==== {}", msg.getPayload());
        userRepo.save(msg.getPayload());
    }
    
    
}
