package pl.java.scalatech;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@EnableZuulProxy
@EnableEurekaClient
@EnableDiscoveryClient
@EnableZipkinStreamServer
@SpringBootApplication
@Slf4j
public class EurekaClientApplication {

    public static void main(String[] args) {
      springPIDAppRun(args, EurekaClientApplication.class);
    }
    
    private static void springPIDAppRun(String[] args,Class<?> clazz) {
        SpringApplication springApplication = new SpringApplication(clazz);
        springApplication.addListeners(new ApplicationPidFileWriter());
        springApplication.run(args);
    }

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    CommandLineRunner dc(DiscoveryClient dc) {
        dc.getServices().stream().forEach(t -> log.info("!!!!!!!!!!!!  {}", t));
        return args -> dc.getInstances("simple-app")
                .forEach(si -> System.out.println(String.format("!!!   %s %s:%s", si.getServiceId(), si.getHost(), si.getPort())));
    }

    @Autowired
    private DiscoveryClient discoveryClient;
    // @formatter:off
     @PostConstruct
     public void post(){
         discoveryClient.getInstances("restaurant-service").
         forEach((ServiceInstance serviceInstance) -> {
         System.out.println(new StringBuilder("Instance -->")
                 .append(serviceInstance.getServiceId())
                 .append("\nServer: ")
                 .append(serviceInstance.getHost())
                 .append(":")
                 .append(serviceInstance.getPort())
                 .append("\nURI: ")
                 .append(serviceInstance.getUri())
                 .append("\n\n\n"));
         });
    }
    // @formatter:on
}
