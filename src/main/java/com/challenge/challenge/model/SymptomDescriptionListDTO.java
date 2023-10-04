package com.challenge.challenge.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SymptomDescriptionListDTO {
    @JsonProperty("SymptomId")
    private Long symptomId;

    @JsonProperty("Description")
    private final String description;


    public SymptomDescriptionListDTO(Long symptomId, String description) {
        this.symptomId = symptomId;
        this.description = description;
    }

    public Long getSymptomId() {
        return symptomId;
    }

    public void setSymptomId(Long symptomId) {
        this.symptomId = symptomId;
    }

    public String getDescription() {
        return description;
    }
}

