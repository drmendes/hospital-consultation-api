package com.challenge.challenge.model;

import jakarta.persistence.*;


@Entity
@Table(name = "consults")
public class Consult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "speciality_id", nullable = false)
    private Speciality speciality;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "pathology_id")
    private Pathology pathology;

    public Consult(Doctor doctor, Speciality speciality, Patient patient, Pathology pathology) {
        this.doctor = doctor;
        this.speciality = speciality;
        this.patient = patient;
        this.pathology = pathology;
    }

    public Consult() {}

    public Long getId() {
        return this.id;
    }

    public Speciality getSpeciality() {
        return this.speciality;
    }

    public Doctor getDoctor() {
        return this.doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setPathology(Pathology pathology) {
        this.pathology = pathology;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public Pathology getPathology() {
        return this.pathology;
    }

    public void setId(long l) {
        this.id = l;
    }
}

