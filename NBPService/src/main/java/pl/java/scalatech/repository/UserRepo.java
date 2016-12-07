package pl.java.scalatech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.domain.User;

public interface UserRepo extends JpaRepository<User, Long>{

}
