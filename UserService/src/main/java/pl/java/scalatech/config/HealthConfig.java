package pl.java.scalatech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HealthConfig {

    private static final String APPLICATION_PID = "application.pid";

    @Bean
    org.springframework.core.io.Resource pid() {
        return new org.springframework.core.io.FileSystemResource(APPLICATION_PID);
    }
}
