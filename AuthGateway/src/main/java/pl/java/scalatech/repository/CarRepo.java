package pl.java.scalatech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import pl.java.scalatech.entity.Car;

public interface CarRepo extends JpaRepository<Car, Long>{

    @PreAuthorize("#oauth2.hasScope('name')")
    List<Car> findByNameLike(String name);
    
    @PreAuthorize("#oauth2.hasScope('color')")
    List<Car> findByColorLike(String name);
}
