package pl.java.scalatech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@RestController
@RefreshScope
@Slf4j
public class ConfigController {

    @Autowired
    RestTemplate restTemplate;
    
    @Value("${user.role:adminSlawek}")
    private String role;
    
    @Value("${slawek:slawek}")
    private String slawek;

    @GetMapping(value = "/role", produces = MediaType.TEXT_PLAIN_VALUE)
    public String role() {
        return String.format("Hello! You role credential is  %s... %s \n", role, slawek);

    }
    
    

    @GetMapping(value = "/role2", produces = MediaType.TEXT_PLAIN_VALUE)
    public String role2(@RequestHeader(value="X-SelfToken") String token) {
        ResponseEntity<String> responseEntity = this.restTemplate.getForEntity("http://search-app/role", String.class);
        log.info("+++ token : {}",token);  
        return responseEntity.getBody();

    }
    
}
