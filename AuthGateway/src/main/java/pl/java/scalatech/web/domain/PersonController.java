package pl.java.scalatech.web.domain;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.java.scalatech.entity.Person;
import pl.java.scalatech.repository.PersonRepo;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonRepo personRepo;
    
    public PersonController(PersonRepo personRepo){
        this.personRepo = personRepo;
    }
    
    @GetMapping("/{id}")
    Person getPersonById(@PathVariable Long id){
        return personRepo.findOne(id);
    }
    @GetMapping("/")
    List<Person> getAll(){
        return personRepo.findAll();
    }
    
    
}
