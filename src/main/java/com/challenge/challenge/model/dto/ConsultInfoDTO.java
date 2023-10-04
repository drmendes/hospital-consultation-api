package com.challenge.challenge.model.dto;

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

    // Add getters for each field

    public Long getConsultId() {
        return consultId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getSpecialityName() {
        return specialityName;
    }

    public String getPatientName() {
        return patientName;
    }

    public Integer getPatientAge() {
        return patientAge;
    }

    public String getPathologyName() {
        return pathologyName;
    }

    // If needed, you can also add setters, but for DTOs, it's often not necessary.
}
