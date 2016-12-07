package pl.java.scalatech.generator;

import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@Profile("populate")
public class RandomConfig {
    @Bean
    Random random(){
        return new Random();
    }
}
