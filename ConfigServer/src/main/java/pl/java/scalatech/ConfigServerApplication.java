package pl.java.scalatech;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableCircuitBreaker
@EnableRetry
public class ConfigServerApplication extends SpringBootServletInitializer{

 
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ConfigServerApplication.class).web(true);
}
    
	public static void main(String[] args) {
	    new SpringApplicationBuilder(ConfigServerApplication.class).web(true).run(args);
	}
	
}
