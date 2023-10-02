package com.challenge.challenge.repository;

import com.challenge.challenge.model.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SymptomRepository extends JpaRepository<Symptom, Long> {
    List<Symptom> findByPathologyId(Long pathologyId);
}
