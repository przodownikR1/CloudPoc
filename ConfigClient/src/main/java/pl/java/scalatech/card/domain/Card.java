package pl.java.scalatech.card.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.domain.AbstractEntity;

@JsonIgnoreProperties(ignoreUnknown=true)
@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Card extends AbstractEntity{
    private static final long serialVersionUID = -757379093260820364L;

    private String cardHolderName;
        
    private String cardNumber;
    
    private String cardType;
    
    private String expiry;
    
    private Currency currency;
     
     
}