package pl.java.scalatech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class ApiGatewayRestController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/role")
    String getRole(@RequestHeader(value="X-SelfToken") String token) {
        log.info("+++ token : {}",token);
        ResponseEntity<String> responseEntity = this.restTemplate.getForEntity("http://search-app/role", String.class);
        return responseEntity.getBody();
    }

    @GetMapping("/users/{id}")
    User getUser(@PathVariable Long id,@RequestHeader(value="X-SelfToken") String token) {
        log.info("+++ token : {}",token);
        ResponseEntity<User> responseEntity = this.restTemplate.getForEntity("http://user-service/users/"+id, User.class);
        return responseEntity.getBody();
    }
    
}