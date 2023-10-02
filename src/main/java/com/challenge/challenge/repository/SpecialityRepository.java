package com.challenge.challenge.repository;

import com.challenge.challenge.model.Consult;
import com.challenge.challenge.model.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecialityRepository extends JpaRepository<Speciality, Long> {
    Optional<Speciality> findByName(String name);
}
