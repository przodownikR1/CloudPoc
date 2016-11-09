package pl.java.scalatech;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Lists;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@EnableZuulProxy
@EnableEurekaClient
@EnableHystrix
@SpringBootApplication
@Slf4j
public class EdgeServerApplication {

    public static void main(String[] args) {
        springPIDAppRun(args, EdgeServerApplication.class);
    }

    private static void springPIDAppRun(String[] args, Class<?> clazz) {
        SpringApplication springApplication = new SpringApplication(clazz);
        springApplication.addListeners(new ApplicationPidFileWriter());
        springApplication.run(args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public SimpleFilter simpleFilter() {
        return new SimpleFilter();
    }

    @RestController
    @RequestMapping("/my/users")
    class UserApiRestController {
        private RestTemplate restTemplate;

        public UserApiRestController(RestTemplate restTemplate) {
            this.restTemplate = restTemplate;
        }

        List<String> fallback() {
            return Lists.newArrayList("unfortunately, the server temporarily down");
        }

        @RequestMapping(method = RequestMethod.GET, value = "/logins")
        @HystrixCommand(fallbackMethod = "fallback")
        List<String> logins() {

            ParameterizedTypeReference<List<Resources<User>>> typeRef = new ParameterizedTypeReference<List<Resources<User>>>() {
            };

            ResponseEntity<List<Resources<User>>> responseEntity = this.restTemplate.exchange("http://user-service/", HttpMethod.GET, null, typeRef);
            responseEntity.getBody().stream().map(r -> r.getContent()).forEach(u -> log.info("loign : {},u"));
            return Lists.newArrayList();

        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class User {
        private String login;
    }
}
