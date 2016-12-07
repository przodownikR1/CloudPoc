package pl.java.scalatech.web;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenerateController {

    @Autowired
    private ProducerTemplate producer;
    
    @RequestMapping("/generate")
    String generate(){
     producer.sendBody("direct:generate", null);   
     return "ok";   
    }
    
}
