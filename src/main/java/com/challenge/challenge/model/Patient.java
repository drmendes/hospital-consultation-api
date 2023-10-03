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

    public String getName() {
        return this.name;
    }

    public Long getId() {
        return this.id;
    }
}
