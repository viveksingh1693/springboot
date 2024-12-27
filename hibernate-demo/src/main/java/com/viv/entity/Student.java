package com.viv.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "student")
public class Student {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


    @ElementCollection
    @CollectionTable(name = "emails", //default student_emails
    joinColumns = @JoinColumn(name = "student_id")) 
    @OrderColumn //default column email_order
    @Column(name = "email") 
    private List<String> email = new ArrayList<>();


    @ElementCollection
    @CollectionTable(name = "image", //default student_images
    joinColumns = @JoinColumn(name = "student_id")) 
    @Column(name = "file_name") //default to images
    private Set<String> images = new HashSet<>();

}
