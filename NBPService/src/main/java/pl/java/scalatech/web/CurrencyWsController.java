package pl.java.scalatech.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import pl.java.scalatech.camel.nbp.CurrentService;

@RestController
public class CurrencyWsController {
    @Autowired
    private CurrentService client;
    
    @GetMapping("/byCodeWs/{code}")
    public String getMutlipierByCode(@PathVariable String code){
        return client.getMultiplierByCode(code);
    }
    
}
