package com.challenge.challenge.repository;

import com.challenge.challenge.model.Consult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Consult, Long> {
}
