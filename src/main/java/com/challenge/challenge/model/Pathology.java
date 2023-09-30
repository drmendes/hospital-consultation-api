package com.challenge.challenge.model;

import javax.persistence.*;

@Entity
@Table(name = "pathologies")
public class Pathology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    // TODO Getter, Setter
}