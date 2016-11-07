package pl.java.scalatech;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

import lombok.SneakyThrows;

@RestController
@Slf4j
public class CircuitController {

    @Autowired 
    private Random random;
    
   int second(String token){
       log.info("+++ token : {}",token);
       return 55;
   }
   @Recover
   int third(RuntimeException ex){
       return 55;
   }
    
    @HystrixCommand(fallbackMethod="second")
    @GetMapping("/busy")
    int derive(@RequestHeader(value="X-SelfToken",required=false) String token){
        log.info("+++ token : {}",token);
        return calculate(); 
    }
    
    @Retryable()
    @GetMapping("/busy2")
    int derive2(@RequestHeader(value="X-SelfToken",required=false) String token){
        log.info("+++ token : {}",token);
        return calculate(); 
    }
    
    
    @SneakyThrows
    int calculate(){
        if(random.nextInt(10)<5){
            Thread.sleep(5000);
            throw new RuntimeException("!!!! crash");
        }
        return 1;
    }
    
}
