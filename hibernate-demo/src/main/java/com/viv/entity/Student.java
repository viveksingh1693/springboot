package com.viv.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.viv.model.Status;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OrderBy;
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

    
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, columnDefinition = "varchar(255) default 'ACTIVE'")
    private Status status = Status.ACTIVE;

    /**
     * Map a collection of Map types (String, String) to a separate table.
     * @ElementCollection is used to store a collection of basic types.
     * @CollectionTable is used to specify the table name for the collection of basic types.
     * @Column is used to specify the column name for the basic type.
     * @JoinColumn is used to specify the foreign key column in the collection table that references the primary table.
     * @MapKeyColumn is used to specify the column name for the key of the map.
     */

    @ElementCollection
    @CollectionTable(name = "addresses", //default student_addresses
        joinColumns = @JoinColumn(name = "student_id"))
    @Column(name = "address") //default to addresses
    @MapKeyColumn(name = "address_type") //default to addresses_key
    private Map<String, String> addresses = new HashMap<>();

    /**
     * Map a collection of List types (String) to a separate table to preserve the order.
     * @ElementCollection is used to store a collection of basic types.
     * @CollectionTable is used to specify the table name for the collection of basic types.
     * @Column is used to specify the column name for the basic type.
     * @OrderColumn is used to specify the column name for the order of the elements in the collection.
     * @JoinColumn is used to specify the foreign key column in the collection table that references the primary table.
     * 
     */
    @ElementCollection
    @CollectionTable(name = "emails", //default student_emails
    joinColumns = @JoinColumn(name = "student_id")) 
    @OrderColumn //default column email_order
    @Column(name = "email") 
    private List<String> email = new ArrayList<>();


    /**
     * Map a collection of Set types (String) to a separate table.
     * @ElementCollection is used to store a collection of basic types.
     * @CollectionTable is used to specify the table name for the collection of basic types.
     * @Column is used to specify the column name for the basic type.
     * @JoinColumn is used to specify the foreign key column in the collection table that references the primary table.
     * 
     */
    @ElementCollection
    @CollectionTable(name = "image", //default student_images
    joinColumns = @JoinColumn(name = "student_id")) 
    @Column(name = "file_name") //default to images
    @OrderBy("file_name DESC") //default to file_name
    private Set<String> images = new HashSet<>();

}
