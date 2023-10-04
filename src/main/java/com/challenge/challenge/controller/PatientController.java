package com.challenge.challenge.controller;


import com.challenge.challenge.exceptions.PatientNotFoundException;
import com.challenge.challenge.model.*;
import com.challenge.challenge.model.Symptom;
import com.challenge.challenge.model.dto.ConsultDoctorSpecialityListDTO;
import com.challenge.challenge.model.dto.SymptomDescriptionListDTO;
import com.challenge.challenge.service.PatientService;
import com.challenge.challenge.service.RecentCommandsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private RecentCommandsService recentCommandsService;

    @GetMapping("/{id}/consults-and-symptoms")
    public ResponseEntity<Map<String, Object>> getConsultsAndSymptoms(@PathVariable Long id) {
        recentCommandsService.addCommand(String.format("GET /api/patients/%d/consults-and-symptoms", id));
        Optional<Patient> optionalPatient = patientService.findById(id);

        optionalPatient.ifPresentOrElse(
                value -> {},
                () -> { throw new PatientNotFoundException("Patient with ID " + id + " not found"); }
        );

        List<Consult> consults = patientService.getConsultsForPatient(id);
        List<Symptom> symptoms = new ArrayList<>();

        for (Consult consult : consults) {
            Optional.ofNullable(consult.getPathology())
                    .ifPresent(pathology -> symptoms.addAll(patientService.getSymptomsForPathology(pathology.getId())));
        }

        List<ConsultDoctorSpecialityListDTO> consultDTOs = consults.stream()
                .map(this::toConsultDoctorSpecialityListDTO)
                .toList();

        List<SymptomDescriptionListDTO> symptomDTOs = symptoms.stream()
                .map(this::toSymptomDescriptionListDTO)
                .toList();


        Map<String, Object> response = new HashMap<>();
        response.put("Consults", consultDTOs);
        response.put("Symptoms", symptomDTOs);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllPatients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age) {

        recentCommandsService.addCommand("GET /api/patients/all");

        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Patient> patients = patientService.getAllPatients(name, age, pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("patients", patients.getContent());
        response.put("totalPages", patients.getTotalPages());
        response.put("currentPage", patients.getNumber() + 1); // Page numbers are 0-indexed
        response.put("totalElements", patients.getTotalElements());
        response.put("sort", Map.of("type", sortBy, "direction", direction));
        Map<String, Object> filterMap = new HashMap<>();
        if (name != null) {
            filterMap.put("name", name);
        }
        if (age != null) {
            filterMap.put("age", age);
        }
        response.put("filter", filterMap);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    private ConsultDoctorSpecialityListDTO toConsultDoctorSpecialityListDTO(Consult consult) {
        return new ConsultDoctorSpecialityListDTO(consult.getId(), consult.getDoctor().getName(), consult.getDoctor().getSpeciality().getName());
    }

    private SymptomDescriptionListDTO toSymptomDescriptionListDTO(Symptom symptom) {
        return new SymptomDescriptionListDTO(symptom.getId(), symptom.getDescription());
    }

}
