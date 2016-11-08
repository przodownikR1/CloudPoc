package pl.java.scalatech;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableTurbine
@EnableDiscoveryClient
public class DashboardHystrixApplication {
   
    @RequestMapping("/")
    public String home() {
        return "forward:/hystrix";
    }
    
    public static void main(String[] args) {
        new SpringApplicationBuilder(DashboardHystrixApplication.class).web(true).run(args);
    }
    
}
