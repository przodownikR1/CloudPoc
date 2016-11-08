package pl.java.scalatech.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.ToString;

@ToString
@MappedSuperclass
public abstract class AbstractEntity {
    @Id @GeneratedValue
    @Getter
    protected Long id;
}
