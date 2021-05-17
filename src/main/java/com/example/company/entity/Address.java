package com.example.company.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String homenumber;

    public Address(String street, String homenumber) {
        this.street = street;
        this.homenumber = homenumber;
    }
}
