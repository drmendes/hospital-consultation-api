
package com.challenge.challenge.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO for creating a new consultation.")
public class ConsultCreationDTO {
    @Schema(description = "Name or ID of the doctor.", example = "Dr. John Doe")
    private String doctor;

    @Schema(description = "Name or ID of the patient.", example = "Jane Smith")
    private String patient;

    @Schema(description = "Pathology (optional).", example = "Flu", required = false)
    private String pathology;

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