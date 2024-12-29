package com.viv.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("Programmer")
@Getter
@Setter
public class Programmer extends Employee {

    private String programmingLanguage;

}
