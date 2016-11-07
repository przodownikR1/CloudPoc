package pl.java.scalatech.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.camel.nbp.NbpCurrentExchange;

@RestController
@Slf4j
public class CurrencyController {
    
    private final NbpCurrentExchange ce;
    //4.3 final nie musi by @Autowired before musi 
    public CurrencyController(NbpCurrentExchange currentExchange){
          this.ce =currentExchange;
    }
    @GetMapping("/byCode/{code}")
    public String getMutlipierByCode(@PathVariable String code,@RequestHeader(value="X-SelfToken") String token){
        log.info("+++ token:  {}",token);
        return ce.getCurrentMultiplier(code);
    }
    
}
