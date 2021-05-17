package com.example.company.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String corpName;
    @Column(nullable = false)
    private String directorName;
    @OneToOne(optional = false)
    private Address address;

    public Company(String corpName, String directorName, Address address) {
        this.corpName = corpName;
        this.directorName = directorName;
        this.address = address;
    }
}
