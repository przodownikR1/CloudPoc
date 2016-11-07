package pl.java.scalatech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@RestController
@RefreshScope
@Slf4j
public class ConfigController {

    @Value("${user.role:adminSlawek}")
    private String role;
    
    @Value("${slawek:slawek}")
    private String slawek;

    @GetMapping(value = "/role", produces = MediaType.TEXT_PLAIN_VALUE)
    public String role(@RequestHeader(value="X-SelfToken") String token) {
        log.info("+++ token : {}",token);
        return String.format("Hello client 2! You role credential is  %s... %s \n", role, slawek);

    }
    @GetMapping("/")
    public String home(@RequestHeader(value="X-SelfToken") String token) {
    log.info("you called home : token : {} ",token);
        return "Hello World";
    }

    @Autowired
    RestTemplate restTemplate;
    
    @GetMapping("/callhome")
    public String callHome(@RequestHeader(value="X-SelfToken") String token) {
    log.info("calling home :  token : {}",token );
        return restTemplate.getForObject("http://localhost:8091", String.class);
    }
}
