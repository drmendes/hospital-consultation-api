package com.challenge.challenge.service;

import com.challenge.challenge.exceptions.PatientNotFoundException;
import com.challenge.challenge.model.Consult;
import com.challenge.challenge.model.Patient;
import com.challenge.challenge.model.Symptom;
import com.challenge.challenge.repository.ConsultRepository;
import com.challenge.challenge.repository.PatientRepository;
import com.challenge.challenge.repository.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ConsultRepository consultRepository;

    @Autowired
    private SymptomRepository symptomRepository;

    @Cacheable(cacheNames = "consults", key = "#patientId")
    public List<Consult> getConsultsForPatient(Long patientId) {
        if (!patientRepository.existsById(patientId)) {
            throw new PatientNotFoundException("Patient with id " + patientId + " not found");
        }
        return consultRepository.findByPatientId(patientId);
    }

    @Cacheable(cacheNames = "symptoms", key = "#pathologyId")
    public List<Symptom> getSymptomsForPathology(Long pathologyId) {
        return symptomRepository.findByPathologyId(pathologyId);
    }

    @Cacheable(cacheNames = "patients", key = "#id")
    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);
    }

    @Cacheable(cacheNames = "allPatients", key = "#name + #age + #pageable.toString()")
    public Page<Patient> getAllPatients(String name, Integer age, Pageable pageable) {
        if (name != null && age != null) {
            return Optional.ofNullable(patientRepository.findByNameContainingAndAge(name, age, pageable))
                    .orElse(Page.empty());
        }
        if (name != null) {
            return Optional.ofNullable(patientRepository.findByNameContaining(name, pageable))
                    .orElse(Page.empty());
        }
        if (age != null) {
            return Optional.ofNullable(patientRepository.findByAge(age, pageable))
                    .orElse(Page.empty());
        }
        return Optional.of(patientRepository.findAll(pageable))
                .orElse(Page.empty());
    }

}