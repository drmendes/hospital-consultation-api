package com.challenge.challenge.controller;

import com.challenge.challenge.exceptions.PatientNotFoundException;
import com.challenge.challenge.model.*;
import com.challenge.challenge.model.Symptom;
import com.challenge.challenge.model.dto.ConsultDoctorSpecialityListDTO;
import com.challenge.challenge.model.dto.SymptomDescriptionListDTO;
import com.challenge.challenge.service.PatientService;
import com.challenge.challenge.service.RecentCommandsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Retrieve consults and symptoms for a given patient ID",
            description = "This endpoint returns consults and associated symptoms for a patient, identified by their ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful retrieval of consults and symptoms."),
            @ApiResponse(responseCode = "404", description = "Patient with the given ID not found.")
    })
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
    @Operation(summary = "Retrieve all patients with optional filtering by name and age.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved patients list."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    public ResponseEntity<Map<String, Object>> getAllPatients(
            @Parameter(description = "Page number for pagination.", example = "1")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of records per page.", example = "10")
            @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Sort by column name.", example = "name")
            @RequestParam(defaultValue = "name") String sortBy,
            @Parameter(description = "Sort direction (asc or desc).", example = "asc")
            @RequestParam(defaultValue = "asc") String direction,
            @Parameter(description = "Filter by patient name.", example = "Jane", required = false)
            @RequestParam(required = false) String name,
            @Parameter(description = "Filter by patient age.", example = "30", required = false)
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
