package com.challenge.challenge.repository;

import com.challenge.challenge.model.Consult;
import com.challenge.challenge.model.dto.ConsultInfoDTO;
import com.challenge.challenge.model.dto.SpecialityPatientCountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConsultRepository extends JpaRepository<Consult, Long> {
    List<Consult> findByPatientId(Long patientId);

    @Query("SELECT new com.challenge.challenge.model.dto.SpecialityPatientCountDTO(" +
            "c.speciality.name, " +
            "COUNT(DISTINCT c.patient.id)) " +  // Removed the int() cast
            "FROM Consult c " +
            "GROUP BY c.speciality.name " +
            "HAVING COUNT(DISTINCT c.patient.id) > :threshold")
    List<SpecialityPatientCountDTO> findSpecialitiesWithPatientCountAboveThreshold(@Param("threshold") int threshold);


    @Query("SELECT new com.challenge.challenge.model.dto.ConsultInfoDTO(" +
            "c.id, " +
            "d.name, " +
            "s.name, " +
            "p.name, p.age, " +
            "pa.name) " +
            "FROM Consult c " +
            "JOIN c.doctor d " +
            "JOIN d.speciality s " +
            "JOIN c.patient p " +
            "LEFT JOIN c.pathology pa")
    List<ConsultInfoDTO> getAllConsultInfo();


}