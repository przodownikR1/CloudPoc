package pl.java.scalatech.generator;

import static com.google.common.collect.Sets.newHashSet;

import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.card.domain.Card;
import pl.java.scalatech.user.domain.User;

@Configuration
//@Profile("populate")
@Slf4j
public class Generator {
    @Autowired
    private Random random;
   
    @Autowired
    private CardGenerator cardGenerator;
    
    @Autowired
    private UserGenerator userGenerator;

    private Set<Card> generateCreditCard() {
        Set<Card> cards = newHashSet();
        for (int j = 0; j < random.nextInt(3); j++) {
            cards.add(cardGenerator.generateSingleCard());
        }
        log.info("card count : {}",cards.size());
        return cards;
    }

   public User generateUser(){
       return userGenerator.generateSingleUser(generateCreditCard());
   }
    
}
