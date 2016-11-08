package pl.java.scalatech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.entity.Person;

public interface PersonRepo  extends JpaRepository<Person, Long>{
    

}
