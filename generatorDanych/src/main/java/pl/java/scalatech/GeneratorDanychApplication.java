package pl.java.scalatech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;
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
		springPIDAppRun(args, GeneratorDanychApplication.class);
	}
	private static void springPIDAppRun(String[] args,Class<?> clazz) {
        SpringApplication springApplication = new SpringApplication(clazz);
        springApplication.addListeners(new ApplicationPidFileWriter());
        springApplication.run(args);
    }
	
	@Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
