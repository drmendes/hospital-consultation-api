package com.challenge.challenge.model.dto;


public class ConsultCreationDTO {
    private String doctor;
    private String patient;

    private String pathology; // Optional

    public ConsultCreationDTO(String doctor, String patient, String pathology) {
        this.doctor = doctor;
        this.patient = patient;
        this.pathology = pathology;
    }

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

