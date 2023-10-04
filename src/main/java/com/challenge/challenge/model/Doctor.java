package com.challenge.challenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;


@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "speciality_id")
    private Speciality speciality;

    public Doctor(long id, String name, Speciality speciality) {
        this.id = id;
        this.name = name;
        this.speciality = speciality;
    }

    public Doctor() {

    }


    public String getName() {
        return name;
    }

    public Speciality getSpeciality() {
        return this.speciality;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }
}
