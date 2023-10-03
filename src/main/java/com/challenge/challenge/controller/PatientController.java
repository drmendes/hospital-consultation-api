package com.challenge.challenge.controller;


import com.challenge.challenge.model.*;
import com.challenge.challenge.model.Symptom;
import com.challenge.challenge.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/{id}/consults-and-symptoms")
    public ResponseEntity<Map<String, Object>> getConsultsAndSymptoms(@PathVariable Long id) {
        Optional<Patient> optionalPatient = patientService.findById(id);

        optionalPatient.orElseThrow(() -> new PatientNotFoundException("Patient with ID " + id + " not found"));

        List<Consult> consults = patientService.getConsultsForPatient(id);
        List<Symptom> symptoms = new ArrayList<>();

        for (Consult consult : consults) {
            Optional.ofNullable(consult.getPathology())
                    .ifPresent(pathology -> symptoms.addAll(patientService.getSymptomsForPathology(pathology.getId())));
        }

        List<ConsultDoctorSpecialityListDTO> consultDTOs = consults.stream()
                .map(this::toConsultDoctorSpecialityListDTO)
                .collect(Collectors.toList());

        List<SymptomDescriptionListDTO> symptomDTOs = symptoms.stream()
                .map(this::toSymptomDescriptionListDTO)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("Consults", consultDTOs);
        response.put("Symptoms", symptomDTOs);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    private ConsultDoctorSpecialityListDTO toConsultDoctorSpecialityListDTO(Consult consult) {
        return new ConsultDoctorSpecialityListDTO(consult.getId(), consult.getDoctor().getName(), consult.getDoctor().getSpeciality().getName());
    }

    private SymptomDescriptionListDTO toSymptomDescriptionListDTO(Symptom symptom) {
        return new SymptomDescriptionListDTO(symptom.getId(), symptom.getDescription());
    }

}
