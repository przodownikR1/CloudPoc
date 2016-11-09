package pl.java.scalatech.config;

import java.io.IOException;
import java.io.InputStreamReader;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class PidHealth implements HealthIndicator{

    
    @Resource
    private org.springframework.core.io.Resource pid;
 
    @PostConstruct
    public void init(){
        try {
            log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!! PID HEALTH :  {}",pid.getFile().length());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
  
    @Override
    public Health health() {        
        return new Health.Builder().status(Status.UP + ": "+retrievePIDFromFile()).build();
    }
     
    @SneakyThrows(IOException.class)
    String retrievePIDFromFile( ){
        log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! {}",pid.getFile().getAbsoluteFile());
        return CharStreams.toString(new InputStreamReader(pid.getInputStream(), Charsets.UTF_8));
    }
    
}
