package pl.java.scalatech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.components.Generator;
import pl.java.scalatech.components.RandomConfig;
import pl.java.scalatech.user.message.UserProcessor;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories
@EnableCircuitBreaker
// @EnableTurbineStream
// @formatter:off
@ComponentScans(
        value={
                @ComponentScan(basePackages="pl.java.scalatech.card"),
                @ComponentScan(basePackages="pl.java.scalatech.user"),        
                @ComponentScan(basePackageClasses=RandomConfig.class),
                @ComponentScan(basePackageClasses=Generator.class),
                @ComponentScan(basePackageClasses=UserProcessor.class),
                @ComponentScan(basePackages="pl.java.scalatech.config")
                })
// @formatter:on
@Slf4j
@EnableBinding(Sink.class)
public class ConfigClientApplication {

    public static void main(String[] args) {
        springPIDAppRun(args, ConfigClientApplication.class);
    }
    private static void springPIDAppRun(String[] args,Class<?> clazz) {
        SpringApplication springApplication = new SpringApplication(clazz);
        springApplication.addListeners(new ApplicationPidFileWriter());
        springApplication.run(args);
    }
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

   /* @Bean
    AlwaysSampler alwaysSampler() {
        return new AlwaysSampler();
    }
*/
}
