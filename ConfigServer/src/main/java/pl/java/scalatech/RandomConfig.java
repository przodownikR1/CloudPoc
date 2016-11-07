package pl.java.scalatech;

import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RandomConfig {

    @Bean
    Random random(){
        return new Random();
    }
    
}
