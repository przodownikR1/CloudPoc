package pl.java.scalatech.web.domain;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import pl.java.scalatech.entity.Car;
import pl.java.scalatech.repository.CarRepo;

@RestController
@RequestMapping("/car")
public class CarController {

    private final CarRepo carRepo;

    public CarController(CarRepo carRepo) {
        this.carRepo = carRepo;
    }

    @GetMapping("/{id}")
    @Timed(name = "find-car-timed")
    @PreAuthorize("#oauth2.hasScope('read')")
    Car getById(@PathVariable Long id) {
        return carRepo.findOne(id);
    }

    @GetMapping("/name/{name}")
    List<Car> findByName(@PathVariable String name) {
        return carRepo.findByNameLike(name);
    }

    @GetMapping("/color/{color}")
    List<Car> findByColor(@PathVariable String color) {
        return carRepo.findByColorLike(color);
    }

    @GetMapping("/")
    List<Car> getAll() {
        return carRepo.findAll();
    }
}
