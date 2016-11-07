package pl.java.scalatech.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
@Component
@Profile("jms")
@Slf4j
public class GenerateDataRoute extends RouteBuilder{

   @Override
    public void configure() throws Exception {
        from("timer://foo?fixedRate=true&period=2000").routeId("timerRoute")
         .bean(UserGenerate.class).to("seda:throttle");
   
         from("seda:throttle").routeId("throttleMessageRoute").throttle(2).timePeriodMillis(20000).to("seda:nextQueue");
         
         from("seda:nextQueue").routeId("sendToQueue").to("activemq:randomUser");
    }

    
}
