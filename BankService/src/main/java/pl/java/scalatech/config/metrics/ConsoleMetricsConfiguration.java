package pl.java.scalatech.config.metrics;


import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableMetrics
@Slf4j
@ConditionalOnProperty(name = "metrics.console.logger")
public class ConsoleMetricsConfiguration extends MetricsConfigurerAdapter{

    
    @Value("${metrics.slf4j.period:2}")
    private long periodMinutes =2;
    
    @Override
    public void configureReporters(MetricRegistry metricRegistry) {
        log.info("+++ console metrics enabled ");
        // registerReporter allows the MetricsConfigurerAdapter to
        // shut down the reporter when the Spring context is closed
        registerReporter(ConsoleReporter
                .forRegistry(metricRegistry)
                .build())
                .start(periodMinutes, TimeUnit.MINUTES);
}
    
  
}