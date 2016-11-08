package pl.java.scalatech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.components.Generator;
import pl.java.scalatech.components.RandomConfig;


@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories
@EnableCircuitBreaker
//@EnableTurbineStream
// @formatter:off
@ComponentScans(
        value={
                @ComponentScan(basePackages="pl.java.scalatech.card"),
                @ComponentScan(basePackages="pl.java.scalatech.user"),        
                @ComponentScan(basePackageClasses=RandomConfig.class),
                @ComponentScan(basePackageClasses=Generator.class)
                })
// @formatter:on
@Slf4j
public class ConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }
    
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
   

           
}
