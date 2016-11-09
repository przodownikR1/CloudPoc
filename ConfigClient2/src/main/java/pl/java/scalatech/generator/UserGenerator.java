package pl.java.scalatech.generator;

import java.math.BigDecimal;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.codahale.metrics.annotation.Timed;
import com.github.javafaker.Faker;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.user.domain.Card;
import pl.java.scalatech.user.domain.Position;
import pl.java.scalatech.user.domain.User;

@Slf4j
@Component
//@Profile("populate")
public class UserGenerator {

    private final Random random;
    private final Faker faker;
    Position[] positions;

    public UserGenerator(Random random) {
        this.random = random;
        faker = new Faker();
        positions = Position.values();
    }

    // @formatter:off
    @Timed(name="userGenerator")
    public User generateSingleUser(Set<Card>cards) {                
        User user = User.builder()
                .email(faker.internet().emailAddress())             
                .login(faker.name().fullName())
                .name(faker.name().lastName())
                .position(positions[random.nextInt(positions.length-1)])
                .salary(new BigDecimal(random.nextInt(10000)))
                .cards(cards)
                .build();
                log.debug("user: {}",user);
        return user;
    }
 // @formatter:on

}