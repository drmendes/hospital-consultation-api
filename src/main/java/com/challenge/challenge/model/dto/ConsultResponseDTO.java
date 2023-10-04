package com.challenge.challenge.model.dto;


public class ConsultResponseDTO {
    private String doctor;
    private String patient;

    private String speciality;
    private String pathology;


    public ConsultResponseDTO(String doctor, String patient, String speciality, String pathology) {
        this.doctor = doctor;
        this.patient = patient;
        this.speciality = speciality;
        this.pathology = pathology;
    }

    public ConsultResponseDTO() {
    }

    public String getDoctor() {
        return doctor;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getPathology() {
        return pathology;
    }

    public void setPathology(String pathology) {
        this.pathology = pathology;
    }

    public void setDoctor(String name) {
        this.doctor = name;
    }
}

