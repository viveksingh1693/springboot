package com.viv.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Authorities")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;


}
