package com.viv.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

/**
 * As its an Embeddable class, it can be embedded in other entities.
 */
@Embeddable
@Data
public class Address {

    private String street;

    private String city;

    private String state;

    private String zipCode;

}
