package pl.java.scalatech.generator;

import java.math.BigDecimal;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.entity.Person;

@Slf4j
@Component
public class UserGenerate {

    private Random r = new Random();
    private Faker faker;
    
  
    public UserGenerate() {
        faker = new Faker();
    }

    // @formatter:off    
    public Person generateSingleUser() { 
        Person user = Person.builder()               
                .city(faker.address().city())                
                .login(faker.name().fullName())
                .name(faker.name().lastName())               
                .salary(new BigDecimal(r.nextInt(10000)))
                .build();              
        return user;
    }
 // @formatter:on
   
  
}
