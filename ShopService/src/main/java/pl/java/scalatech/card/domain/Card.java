package pl.java.scalatech.card.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown=true)
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Card {
    
    private Long id;
    
    private String cardHolderName;
        
    private String cardNumber;
    
    private String cardType;
    
    private String expiry;
    
    private Currency currency;
     
     
}