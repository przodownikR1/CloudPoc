package pl.java.scalatech.config;


import static com.google.common.collect.Lists.newArrayList;
import java.util.List;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.feature.Feature;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class CxfConfig {
    private static final String SHUTDOWN = "shutdown";
    private static final String CXF = "cxf";
    private static final String SOAP = "/soap/*";
    private static final String CXF_SERVLET = "CXFServlet";

    @Bean
    public ServletRegistrationBean soapServletRegistrationBean() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new CXFServlet(), SOAP);
        registration.setName(CXF_SERVLET);
        return registration;
    }

    @Bean(name = CXF, destroyMethod = SHUTDOWN)
    public SpringBus configureCxfBus() {
        final SpringBus bus = new SpringBus();
        List<Feature> features = newArrayList();
        features.add(loggingFeature());
        bus.setFeatures(features);
        bus.setId(CXF);        
        return bus;
}
    
      private LoggingFeature loggingFeature() {
        LoggingFeature loggingFeature = new LoggingFeature();       
        loggingFeature.setPrettyLogging(true);
        return loggingFeature;
    }

}