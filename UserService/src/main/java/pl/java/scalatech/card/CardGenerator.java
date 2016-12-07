package pl.java.scalatech.card;

import java.util.Random;

import org.springframework.stereotype.Component;

import com.codahale.metrics.annotation.Timed;
import com.github.javafaker.Faker;

import pl.java.scalatech.card.domain.Card;
import pl.java.scalatech.card.domain.Currency;
@Component
//@Profile("populate")
public class CardGenerator {
        
    private final Random random;
    private final Faker faker;    
    Currency[] currencies;
       
    public CardGenerator(Random random) {
        this.random =random;
        faker = new Faker();        
        currencies = Currency.values();
    }
  // @formatter:off
    @Timed(name="cardGenerator")
    public Card generateSingleCard() {
     Card card = Card.builder()
     .currency(currencies[random.nextInt(currencies.length-1)])
     .cardNumber(faker.business().creditCardNumber())
     .expiry(faker.business().creditCardExpiry())
     .cardHolderName(faker.company().industry()+"_"+faker.numerify("CARD???MY"))
     .cardType(faker.business().creditCardType()).build();
    
     return card;
        
    }    
     // @formatter:on
    
}
