package com.challenge.challenge.model.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConsultInfoDTO {
    private Long consultId;
    private String doctorName;
    private String specialityName;
    private String patientName;
    private Integer patientAge;
    private String pathologyName;

    public ConsultInfoDTO(Long consultId, String doctorName, String specialityName, String patientName, Integer patientAge, String pathologyName) {
        this.consultId = consultId;
        this.doctorName = doctorName;
        this.specialityName = specialityName;
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.pathologyName = pathologyName;
    }

}
