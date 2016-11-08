package pl.java.scalatech.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.java.scalatech.entity.Car;
import pl.java.scalatech.repository.CarRepo;

@RestController
@RequestMapping("/api/car")
public class CarController {
    
    private final CarRepo carRepo;
    
   
     public CarController(CarRepo carRepo) {
     this.carRepo = carRepo;
    }
    
    @GetMapping("/{id}")
    Car getById(@PathVariable Long id) {
        return carRepo.findOne(id);
    }
    
    @GetMapping("/name/{name}")
    List<Car> findByName(@PathVariable String name){
        return carRepo.findByNameLike(name);
    }
    
    @GetMapping("/color/{color}")   
    List<Car> findByColor(@PathVariable String color){
        return carRepo.findByColorLike(color);
    }
    
    @GetMapping("/")
    List<Car> getAll() {
        return carRepo.findAll();
    }
}
