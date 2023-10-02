package com.challenge.challenge.model;


public class ConsultCreationDTO {
    private String doctor;
    private String patient;

    private String pathology; // Optional

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getPathology() {
        return pathology;
    }
}

