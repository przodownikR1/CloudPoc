package pl.java.scalatech.image.config;

import static com.google.common.collect.Maps.newHashMap;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

import lombok.Data;
@Data
@Component
public class RegisterMap {
    
    Map<String,String> register= newHashMap();
   
}
