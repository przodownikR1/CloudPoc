package pl.java.scalatech.components;

import static com.google.common.collect.Sets.newHashSet;

import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.card.CardGenerator;
import pl.java.scalatech.card.domain.Card;
import pl.java.scalatech.user.UserGenerator;
import pl.java.scalatech.user.repo.UserRepo;

@Configuration
//@Profile("populate")
@Slf4j
public class Generator implements CommandLineRunner {

    @Autowired
    private Random random;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CardGenerator cardGenerator;    
    @Autowired
    private UserGenerator userGenerator;

    private Set<Card> generateCreditCard() {
        Set<Card> cards = newHashSet();
        for (int j = 0; j < random.nextInt(3); j++) {
            cards.add(cardGenerator.generateSingleCard());
        }
       log.debug("card count : {}",cards.size());
        return cards;
    }

    @Override
    public void run(String... args) throws Exception {        
        random.ints(1,200).limit(random.nextInt(200)).parallel().forEach(value -> userRepo.save(userGenerator.generateSingleUser(generateCreditCard())));                
    }
}
