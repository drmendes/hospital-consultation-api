
package com.challenge.challenge.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO for the response of a consultation creation or retrieval.")
public class ConsultResponseDTO {
    @Schema(description = "Name or ID of the doctor.", example = "Dr. John Doe")
    private String doctor;

    @Schema(description = "Name or ID of the patient.", example = "Jane Smith")
    private String patient;

    @Schema(description = "Speciality of the doctor.", example = "Cardiology")
    private String speciality;

    @Schema(description = "Pathology of the patient.", example = "Flu")
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