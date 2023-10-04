package com.challenge.challenge.controller;

import com.challenge.challenge.model.dto.SpecialityPatientCountDTO;
import com.challenge.challenge.service.RecentCommandsService;
import com.challenge.challenge.service.SpecialityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private RecentCommandsService recentCommandsService;

    @ApiOperation(value = "Get top specialties by patient count")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved data"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/top")
    public ResponseEntity<List<SpecialityPatientCountDTO>> getTopSpecialities(@RequestParam Optional<Integer> threshold) {
        recentCommandsService.addCommand("GET /api/specialities/top");

        List<SpecialityPatientCountDTO> specialties = specialityService.getSpecialitiesWithPatientCountAboveThreshold(threshold);
        return new ResponseEntity<>(specialties, HttpStatus.OK);
    }
}
