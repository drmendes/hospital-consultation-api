package com.challenge.challenge.controller;

import com.challenge.challenge.model.Consult;
import com.challenge.challenge.model.Patient;
import com.challenge.challenge.service.ConsultService;
import com.challenge.challenge.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consults")
public class ConsultController {

    @Autowired
    private ConsultService consultService;

    @PostMapping
    public ResponseEntity<Consult> createConsult(@RequestBody Consult consult) {
        Consult savedConsult = consultService.saveConsult(consult);
        return new ResponseEntity<>(savedConsult, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Consult>> getAllConsults() {
        return ResponseEntity.ok(consultService.getAllConsults());
    }
}
