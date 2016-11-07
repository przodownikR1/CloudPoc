package pl.java.scalatech;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;

@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
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
