package com.challenge.challenge.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pathologies")
public class Pathology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public Pathology(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Pathology() {

    }

    public String getName() {
        return this.name;
    }

    public Long getId() {
        return this.id;
    }
}
