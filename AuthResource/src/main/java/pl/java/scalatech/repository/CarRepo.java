package pl.java.scalatech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import pl.java.scalatech.entity.Car;

public interface CarRepo extends JpaRepository<Car, Long>{


    List<Car> findByNameLike(String name);
    

    List<Car> findByColorLike(String name);
}
