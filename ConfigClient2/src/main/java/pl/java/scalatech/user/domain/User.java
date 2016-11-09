package pl.java.scalatech.user.domain;

import java.math.BigDecimal;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User{

    private Long id;
    
    private String name, email, city, country, login;
    
    private BigDecimal salary;
    
    private Position position;

    public Set<Card> cards;

}