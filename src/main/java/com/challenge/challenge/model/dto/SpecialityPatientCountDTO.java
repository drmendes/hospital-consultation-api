package com.challenge.challenge.model.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SpecialityPatientCountDTO {

    @JsonProperty("SpecialtyName")
    private String specialityName;


    @JsonProperty("NumberOfPatients")
    private int numberOfPatients;

    public SpecialityPatientCountDTO(String specialityName, int numberOfPatients) {
        this.specialityName = specialityName;
        this.numberOfPatients = numberOfPatients;
    }

    public int getPatientCount() {
        return numberOfPatients;
    }

    public void setNumberOfPatients(int numberOfPatients) {
        this.numberOfPatients = numberOfPatients;
    }

    public String getSpecialityName() {
        return specialityName;
    }

    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }

}