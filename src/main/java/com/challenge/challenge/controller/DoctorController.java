package com.challenge.challenge.controller;

import com.challenge.challenge.model.Doctor;
import com.challenge.challenge.service.DoctorService;
import com.challenge.challenge.service.RecentCommandsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private RecentCommandsService recentCommandsService;

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        recentCommandsService.addCommand("GET /api/doctors");

        List<Doctor> doctors = doctorService.getAllDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }


}
