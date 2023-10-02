package com.challenge.challenge.repository;

import com.challenge.challenge.model.Consult;
import com.challenge.challenge.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
