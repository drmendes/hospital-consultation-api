package com.challenge.challenge.repository;

import com.challenge.challenge.model.Consult;
import com.challenge.challenge.model.Pathology;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PathologyRepository extends JpaRepository<Pathology, Long> {
    Optional<Pathology> findByName(String pathology);
}
