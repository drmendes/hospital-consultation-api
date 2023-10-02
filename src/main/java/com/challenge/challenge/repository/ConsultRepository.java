package com.challenge.challenge.repository;

import com.challenge.challenge.model.Consult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultRepository extends JpaRepository<Consult, Long> {
    List<Consult> findByPatientId(Long patientId);
}
