package pl.java.scalatech.user.domain;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.card.domain.Card;
import pl.java.scalatech.domain.AbstractEntity;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User extends AbstractEntity{

    private static final long serialVersionUID = -7295202977921565282L;
    private String name, email, city, country, login;
    private BigDecimal salary;
    
    private Position position;

    @OneToMany(cascade=CascadeType.ALL)
    public Set<Card> cards;

}