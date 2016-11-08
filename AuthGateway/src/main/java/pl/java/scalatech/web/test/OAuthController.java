package pl.java.scalatech.web.test;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.entity.Car;

@RestController
@Slf4j
public class OAuthController {

    @Value("${API_BASE_URL}")
    private String baseUrl;

    @Autowired
    OAuth2RestOperations restTemplate;
    
    @RequestMapping("/now")
    String now() {
        return new Date().toString();
    }
    
    @RequestMapping("/test")
    String home() {
        return restTemplate.getForObject(baseUrl + "user", String.class);
    }
    @GetMapping("/clientCar")
    Car clientCar() {
        return restTemplate.getForObject(baseUrl + "car/1", Car.class);
    }
    @GetMapping("/oauth")
    String hello(OAuth2Authentication authentication) {
        return "Hello " + authentication.getName();
    }

}
