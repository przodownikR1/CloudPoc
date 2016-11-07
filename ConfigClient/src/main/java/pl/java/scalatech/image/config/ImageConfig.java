package pl.java.scalatech.image.config;

import static java.util.Arrays.stream;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Maps;
import com.netflix.discovery.converters.Auto;

import javaslang.control.Option;
import javaslang.control.Try;
import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.image.Image;
import pl.java.scalatech.image.repo.ImageRepository;
import pl.java.scalatech.user.domain.User;
import pl.java.scalatech.user.repo.UserRepo;

@Configuration
@Slf4j
@ComponentScan(basePackages="pl.java.scalatech.image")
public class ImageConfig {
    
    @Bean
    Map<String,String> registerImages(){
        return Maps.newHashMap(); 
    }
    
    @Autowired
    private RegisterMap registerImages;
    
    @Autowired
    private UserRepo userRepo;
    
    @Bean
    org.springframework.core.io.Resource picture() {
        return new org.springframework.core.io.ClassPathResource("new_mg.png");
    }
    
    @Bean
    org.springframework.core.io.Resource picture1() {
        return new org.springframework.core.io.ClassPathResource("cloud1.jpeg");
    }
    
    @Bean
    org.springframework.core.io.Resource picture2() {
        return new org.springframework.core.io.ClassPathResource("cloud2.jpeg");
    }
    
    @Bean
    org.springframework.core.io.Resource picture3() {
        return new org.springframework.core.io.ClassPathResource("cloud3.jpeg");
    }
    
    @Bean
    org.springframework.core.io.Resource picture4() {
        return new org.springframework.core.io.ClassPathResource("cloud4.jpeg");
    }
    
    @Bean
    org.springframework.core.io.Resource picture5() {
        return new org.springframework.core.io.ClassPathResource("cloud5.jpeg");
    }
    
    
    @Bean
    org.springframework.core.io.Resource picture6() {
        return new org.springframework.core.io.ClassPathResource("cloud6.jpeg");
    }
    
    
    @Resource
    private org.springframework.core.io.Resource images[];
    // @formatter:off
    @Bean
    CommandLineRunner commandLineRunner(ImageRepository imageRepository){
        return args -> stream(images).forEach(picture -> {
            try {
                registerImages.getRegister().put(imageRepository.save(picture.getFile()),
                        picture.getFilename());
            } catch (IOException e) {
                
                e.printStackTrace();
            }
        });
    }
 // @formatter:on    
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("10000KB");
        factory.setMaxRequestSize("10000KB");
        return factory.createMultipartConfig();
    }

  
}
