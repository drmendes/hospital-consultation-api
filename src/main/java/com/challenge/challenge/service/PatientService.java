package com.challenge.challenge.service;

import com.challenge.challenge.model.Consult;
import com.challenge.challenge.model.Patient;
import com.challenge.challenge.model.Symptom;
import com.challenge.challenge.repository.ConsultRepository;
import com.challenge.challenge.repository.PatientRepository;
import com.challenge.challenge.repository.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Page<Patient> getAllPatients(Pageable pageable) {
        return patientRepository.findAll(pageable);
    }

    public List<Consult> getConsultsForPatient(Long patientId) {
        return consultRepository.findByPatientId(patientId);
    }

    public List<Symptom> getSymptomsForPathology(Long pathologyId) {
        return symptomRepository.findByPathologyId(pathologyId);
    }

    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);
    }
}
