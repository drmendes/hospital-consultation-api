package com.challenge.challenge.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConsultDoctorSpecialityListDTO {

        @JsonProperty("ConsultId")
        private Long consultId;
        @JsonProperty("Doctor")
        private String doctor;
        @JsonProperty("Speciality")
        private String speciality;

        public ConsultDoctorSpecialityListDTO(Long consultId, String doctor, String speciality) {
                this.consultId = consultId;
                this.doctor = doctor;
                this.speciality = speciality;
        }

        public Long getConsultId() {
                return consultId;
        }

        public void setConsultId(Long consultId) {
                this.consultId = consultId;
        }

        public String getDoctor() {
                return doctor;
        }

        public void setDoctor(String doctor) {
                this.doctor = doctor;
        }

        public String getSpeciality() {
                return speciality;
        }

        public void setSpeciality(String speciality) {
                this.speciality = speciality;
        }
}

