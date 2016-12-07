package pl.java.scalatech.card.web;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.card.domain.Card;
import pl.java.scalatech.card.repo.CardRepo;
import pl.java.scalatech.web.GenericController;

@RestController
@Slf4j
@RequestMapping("/api/card")
public class CardController extends GenericController<Card> {

    private final CardRepo cardRepo;
    
    
    public CardController(JpaRepository<Card, Long> repo) {
        super(repo);
        this.cardRepo =(CardRepo) repo;
    }
    
    @Override
    public Class<?> getDomainClass() {
        return Card.class;
    }
    
}
