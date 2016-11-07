package pl.java.scalatech.domain;

import static com.google.common.collect.Sets.newHashSet;

import java.util.Random;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration;
import org.springframework.cloud.netflix.eureka.config.EurekaClientConfigServerAutoConfiguration;
import org.springframework.cloud.netflix.eureka.config.EurekaDiscoveryClientConfigServiceAutoConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import pl.java.scalatech.card.CardGenerator;
import pl.java.scalatech.card.domain.Card;
import pl.java.scalatech.card.repo.CardRepo;
import pl.java.scalatech.user.repo.UserRepo;

@EnableAutoConfiguration(exclude={EurekaDiscoveryClientConfigServiceAutoConfiguration.class,EurekaClientAutoConfiguration.class,EurekaClientConfigServerAutoConfiguration.class})
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("populate")
@Ignore 
//TODO repair this
public class UserRepoSaveTest {

    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private Random random;

    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private CardRepo cardRepo;
    
    @Autowired 
    private CardGenerator cardGenerator;
    
    /*@Autowired 
    private UserGenerator userGenerator;*/
    
    @Test
    public void shouldSaveEntities(){
        //Assertions.assertThat(userGenerate).isNotNull();
        Assertions.assertThat(cardGenerator).isNotNull();
        Assertions.assertThat(cardRepo).isNotNull();
        Assertions.assertThat(userRepo).isNotNull();
       /* User user = userGenerate.generateSingleUser(generateCreditCard());
        entityManager.persist(user);
        Assertions.assertThat(userRepo.count()).isEqualTo(1l);
        Assertions.assertThat(cardRepo.count()).isGreaterThan(0l);*/
    }
    
    private Set<Card> generateCreditCard() {
        Set<Card> cards = newHashSet();
         for(int j =0 ;j<random.nextInt(3);j++){
             cards.add(cardGenerator.generateSingleCard());
         }
        return cards;
    }
    
}
