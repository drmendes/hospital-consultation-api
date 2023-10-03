package com.challenge.challenge.service;

import com.challenge.challenge.model.*;
import com.challenge.challenge.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultService {
    @Autowired
    private DoctorRepository doctorRepository;  // Assuming you have this repository

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ConsultRepository consultRepository;

    @Autowired
    private PathologyRepository pathologyRepository;

    public ConsultResponseDTO createConsult(ConsultCreationDTO consultDto) {
        // Retrieve the doctor by name
        Doctor doctor = doctorRepository.findByName(consultDto.getDoctor())
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));

        // Retrieve the patient by name
        Patient patient = patientRepository.findByName(consultDto.getPatient())
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));

        // If pathology is provided, retrieve and validate it
        Pathology pathology = null;
        if (consultDto.getPathology() != null) {
            pathology = pathologyRepository.findByName(consultDto.getPathology())
                    .orElseThrow(() -> new EntityNotFoundException("Pathology not found"));
        }

        // Create a new consult with the retrieved doctor and patient
        Consult consult = new Consult();
        consult.setDoctor(doctor);
        consult.setPatient(patient);
        consult.setSpeciality(doctor.getSpeciality());
        consult.setPathology(pathology);

        Consult savedConsult = consultRepository.save(consult);

        return toConsultResponseDTO(savedConsult);
    }


    private ConsultResponseDTO toConsultResponseDTO(Consult consult) {
        ConsultResponseDTO responseDTO = new ConsultResponseDTO();
        responseDTO.setDoctor(consult.getDoctor().getName());
        responseDTO.setPatient(consult.getPatient().getName());
        responseDTO.setSpeciality(consult.getSpeciality().getName());
        if (consult.getPathology() != null) {
            responseDTO.setPathology(consult.getPathology().getName());
        }
        return responseDTO;
    }

    public List<Consult> getAllConsults() {
        return consultRepository.findAll();
    }

}


