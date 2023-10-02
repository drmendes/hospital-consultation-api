package com.challenge.challenge.controller;

import com.challenge.challenge.model.Consult;
import com.challenge.challenge.model.Patient;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/{id}/consults-and-symptoms")
    public ResponseEntity<Map<String, Object>> getConsultsAndSymptoms(@PathVariable Long id) {
        List<Consult> consults = patientService.getConsultsForPatient(id);
        List<Symptom> symptoms = new ArrayList<>();
        for (Consult consult : consults) {
            //symptoms.addAll(patientService.getSymptomsForPathology(consult.getPathology().getId()));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("Consults", consults);
        response.put("Symptoms", symptoms);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Additional endpoints if needed...
}
