package com.challenge.challenge.model;

import jakarta.persistence.*;


@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    public Patient(long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Patient() {
    }

    public String getName() {
        return this.name;
    }

    public Long getId() {
        return this.id;
    }

    public Integer getAge() {
        return this.age;
    }
}
