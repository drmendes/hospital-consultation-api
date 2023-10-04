package com.challenge.challenge.repository;

import com.challenge.challenge.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByName(String patient);

    Page<Patient> findByNameContaining(String name, Pageable pageable);

    Page<Patient> findByAge(Integer age, Pageable pageable);

    Page<Patient> findByNameContainingAndAge(String name, Integer age, Pageable pageable);

}
