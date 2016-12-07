package pl.java.scalatech.camel.nbp;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CurrencyWsRoute extends RouteBuilder{

    @Override
    public void configure() throws Exception {
       from("cxf:/CurrencyCode?address=/byCode&serviceClass="+CurrentService.class.getName()).routeId("wsCxf")
       .beanRef("currentExchange","getCurrentMultiplier");
    }

}
