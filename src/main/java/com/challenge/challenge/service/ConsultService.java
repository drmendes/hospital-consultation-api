package com.challenge.challenge.service;

import com.challenge.challenge.controller.DoctorNotFoundException;
import com.challenge.challenge.controller.PathologyNotFoundException;
import com.challenge.challenge.controller.PatientNotFoundException;
import com.challenge.challenge.model.*;
import com.challenge.challenge.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ConsultRepository consultRepository;

    @Autowired
    private PathologyRepository pathologyRepository;


    public ConsultResponseDTO createConsult(ConsultCreationDTO consultDto) {
        // Retrieve the doctor by name
        Doctor doctor = doctorRepository.findByName(consultDto.getDoctor())
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found"));

        // Retrieve the patient by name
        Patient patient = patientRepository.findByName(consultDto.getPatient())
                .orElseThrow(() -> new PatientNotFoundException("Patient not found"));

        // If pathology is provided, retrieve and validate it
        Pathology pathology = null;
        if (consultDto.getPathology() != null) {
            pathology = pathologyRepository.findByName(consultDto.getPathology())
                    .orElseThrow(() -> new PathologyNotFoundException("Pathology not found"));
        }

        // Create a new consult with the retrieved doctor and patient
        Consult savedConsult = consultRepository.save(new Consult(doctor, doctor.getSpeciality(), patient, pathology));

        return new ConsultResponseDTO(
                String.valueOf(savedConsult.getId()), savedConsult.getDoctor().getName(),
                savedConsult.getPatient().getName(),
                savedConsult.getPathology() != null ? savedConsult.getPathology().getName() : null);
    }


    public List<Consult> getAllConsults() {
        return consultRepository.findAll();
    }

}


