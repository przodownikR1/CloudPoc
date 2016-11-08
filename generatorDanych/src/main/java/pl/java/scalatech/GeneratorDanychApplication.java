package pl.java.scalatech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import io.hawt.springboot.EnableHawtio;
import io.hawt.web.AuthenticationFilter;

@SpringBootApplication
@EnableHawtio
@EnableDiscoveryClient
//@EnableTurbineStream
//@EnableHystrix
public class GeneratorDanychApplication {

	public static void main(String[] args) {
	    System.setProperty(AuthenticationFilter.HAWTIO_AUTHENTICATION_ENABLED, "false");
		SpringApplication.run(GeneratorDanychApplication.class, args);
	}
	@Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
