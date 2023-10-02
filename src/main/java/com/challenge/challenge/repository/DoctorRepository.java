package com.challenge.challenge.repository;

import com.challenge.challenge.model.Consult;
import com.challenge.challenge.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
