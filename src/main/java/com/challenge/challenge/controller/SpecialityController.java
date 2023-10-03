package com.challenge.challenge.controller;

import com.challenge.challenge.model.Patient;
import com.challenge.challenge.model.dto.SpecialityPatientCountDTO;
import com.challenge.challenge.service.PatientService;
import com.challenge.challenge.service.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/specialities")
public class SpecialityController {

    @Autowired
    private SpecialityService specialityService;


    @GetMapping("/top")
    public ResponseEntity<List<SpecialityPatientCountDTO>> getTopSpecialities(@RequestParam Optional<Integer> threshold) {
        List<SpecialityPatientCountDTO> specialties = specialityService.getSpecialitiesWithPatientCountAboveThreshold(threshold);
        return new ResponseEntity<>(specialties, HttpStatus.OK);
    }
}
