package com.challenge.challenge.model;

import javax.persistence.*;

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

    // TODO Getter, Setter
}
