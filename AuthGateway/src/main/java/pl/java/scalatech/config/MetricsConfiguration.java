package pl.java.scalatech.config;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.MetricSet;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;
import com.codahale.metrics.jvm.GarbageCollectorMetricSet;
import com.codahale.metrics.jvm.MemoryUsageGaugeSet;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableMetrics
@Slf4j
public class MetricsConfiguration extends MetricsConfigurerAdapter{

    @Autowired
    private MetricRegistry metricRegistry;

    @PostConstruct
    public void startGraphiteReporter() throws UnknownHostException {
        String hostname = InetAddress.getLocalHost().getHostName();

        Graphite graphite = new Graphite("127.0.0.1", 2003);
        GraphiteReporter reporter = GraphiteReporter
                .forRegistry(metricRegistry)
                .prefixedWith("services.lunchbox." + hostname)
                .build(graphite);
        reporter.start(10, TimeUnit.SECONDS);
    }

 /*   @Override
    public void configureReporters(MetricRegistry metricRegistry) {
        // registerReporter allows the MetricsConfigurerAdapter to
        // shut down the reporter when the Spring context is closed
        registerReporter(ConsoleReporter
                .forRegistry(metricRegistry)
                .build())
                .start(1, TimeUnit.MINUTES);
}*/
    
    
    @PostConstruct
    public void registerJvmMetrics() {
        registerAll("gc", new GarbageCollectorMetricSet(), metricRegistry);
        registerAll("memory", new MemoryUsageGaugeSet(), metricRegistry);
    }

    private void registerAll(String prefix, MetricSet metricSet, MetricRegistry registry) {
        log.info("+++++ metricsSet : {} , registry {}  , prefix : {}  ", metricSet,registry,prefix);
        for (Map.Entry<String, Metric> entry : metricSet.getMetrics().entrySet()) {
            if (entry.getValue() instanceof MetricSet) {
                registerAll(prefix + "." + entry.getKey(), (MetricSet) entry.getValue(), registry);
            } else {
                registry.register(prefix + "." + entry.getKey(), entry.getValue());
            }
        }
    }
}