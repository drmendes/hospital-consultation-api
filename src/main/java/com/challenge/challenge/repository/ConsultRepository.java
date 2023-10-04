package com.challenge.challenge.repository;

import com.challenge.challenge.model.Consult;
import com.challenge.challenge.model.dto.SpecialityPatientCountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConsultRepository extends JpaRepository<Consult, Long> {
    List<Consult> findByPatientId(Long patientId);

    @Query("SELECT new com.challenge.challenge.model.dto.SpecialityPatientCountDTO(c.speciality.name, int(COUNT(DISTINCT c.patient.id))) " +
            "FROM Consult c " +
            "GROUP BY c.speciality.name " +
            "HAVING COUNT(DISTINCT c.patient.id) > :threshold")
    List<SpecialityPatientCountDTO> findSpecialitiesWithPatientCountAboveThreshold(@Param("threshold") int threshold);
}