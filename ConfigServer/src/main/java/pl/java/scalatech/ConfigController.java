package pl.java.scalatech;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

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
    public String role(@RequestHeader(value="X-SelfToken",required=false) String token) {
        log.info("+++ token : {}",token);
        return String.format("Hello! You role credential is  %s... %s \n", role, slawek);

    }
    @PostConstruct
    public void init(){
       // log.info("%%%%%%%%%%%%%%%%%%%%%%%  {}",Integer.valueOf(System.getProperty("numbers")));
    }

}
