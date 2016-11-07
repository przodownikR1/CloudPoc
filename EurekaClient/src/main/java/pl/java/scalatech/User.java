package pl.java.scalatech;

import lombok.Data;


@Data
public class User {

    Long id;

    String name;

    User() {
    }

    public User(String name) {
        this.name = name;
    }

}