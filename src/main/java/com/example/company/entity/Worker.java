package com.example.company.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String workerName;
    @Column(nullable = false,unique = true)
    private String phoneNumber;
    @OneToOne(optional = false)
    private Address address;
    @ManyToOne(optional = false)
    private Department department;

    public Worker(String workerName, String phoneNumber, Address address, Department department) {
        this.workerName = workerName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.department = department;
    }
}
