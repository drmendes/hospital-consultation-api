package com.challenge.challenge.model;

import jakarta.persistence.*;

@Entity
@Table(name = "specialities")
public class Speciality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public Speciality(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Speciality() {
    }

    public String getName() {
        return name;
    }
}
