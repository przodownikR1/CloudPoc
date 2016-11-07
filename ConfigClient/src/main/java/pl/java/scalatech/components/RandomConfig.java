package pl.java.scalatech.components;

import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
//@Profile("populate")
public class RandomConfig {
    @Bean
    Random random(){
        return new Random();
    }
}
