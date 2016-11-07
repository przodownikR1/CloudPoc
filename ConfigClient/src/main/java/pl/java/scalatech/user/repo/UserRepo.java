package pl.java.scalatech.user.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codahale.metrics.annotation.Timed;

import pl.java.scalatech.user.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByName(String name);
    
    @Timed(name="findByLogin")
    Optional<User> findByLogin(String login);

}
