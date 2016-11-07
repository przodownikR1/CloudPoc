package pl.java.scalatech.web;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.annotation.Gauge;

@RestController
public class MetricController {

    @Autowired
    private Random random;
    
    @Autowired
    private MetricRegistry metricRegistry;
    
    @GetMapping("/customMetric")
    MetricRegistry getMetric(){
        return metricRegistry;
    }
    
    @Gauge(name = "random.testGauge", absolute = true)
    public int testGauge() {
        return random.nextInt(100);
}
}
