package pl.java.scalatech.camel;

import pl.java.scalatech.domain.User;

public class HeaderPosition {
   
    public String getPosition(User user){
        return user.getPosition().name();
    }
   
}
