package pl.java.scalatech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.java.scalatech.domain.User;
import pl.java.scalatech.repository.UserRepo;

@Component("userService")
@Transactional
public class UserService {

    @Autowired
    private UserRepo userRepo;
    
    public void saveUser(User user){
        userRepo.save(user);
    }
}
