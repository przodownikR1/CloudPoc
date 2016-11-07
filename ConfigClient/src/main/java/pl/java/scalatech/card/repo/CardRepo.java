package pl.java.scalatech.card.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.card.domain.Card;

public interface CardRepo extends JpaRepository<Card, Long>{

}
