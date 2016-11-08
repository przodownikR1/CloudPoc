package pl.java.scalatech;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pl.java.scalatech.entity.Person;
import pl.java.scalatech.generator.CarGenerator;
import pl.java.scalatech.generator.UserGenerate;
import pl.java.scalatech.repository.CarRepo;
import pl.java.scalatech.repository.PersonRepo;

@SpringBootApplication
public class SampleResourceApplication implements CommandLineRunner{
    
    @Autowired
    private CarRepo carRepo;
    @Autowired
    private PersonRepo personRepo;
    @Autowired
    private CarGenerator carGenerator;
    @Autowired  
    private UserGenerate userGenerator;
    
    private Random carR = new Random();
    
    public static void main(String[] args) {
        SpringApplication.run(SampleResourceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        populateDB();
        
        
    }

    private void populateDB() {
        for(int i = 0 ;i<30;i++){
        Person person = personRepo.save(userGenerator.generateSingleUser());
        for(int j=0 ;j<carR.nextInt(4);j++)
        carRepo.save(carGenerator.generateSingleCar(person));        
        }
    }

    
   
}