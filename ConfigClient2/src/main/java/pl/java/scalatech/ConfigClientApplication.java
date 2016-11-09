package pl.java.scalatech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import pl.java.scalatech.user.UserChannel;
@EnableDiscoveryClient
@SpringBootApplication
@EnableBinding(UserChannel.class)
@EnableScheduling
public class ConfigClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplication.class, args);
	}
	@Bean
	public RestTemplate getRestTemplate() {
	    return new RestTemplate();
	}

}
