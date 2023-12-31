package com.challenge.challenge.model;

import jakarta.persistence.*;

@Entity
@Table(name = "symptoms")
public class Symptom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "pathology_id")
    private Pathology pathology;

    public Symptom() {}

    public Symptom(Long id, String description, Pathology pathology) {
        this.id = id;
        this.description = description;
        this.pathology = pathology;
    }

    public Long getId() {
        return this.id;
    }

    public Pathology getPathology() {
        return pathology;
    }

    public String getDescription() {
        return this.description;
    }

}
