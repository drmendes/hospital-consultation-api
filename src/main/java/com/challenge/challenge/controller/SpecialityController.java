
package com.challenge.challenge.controller;

import com.challenge.challenge.model.dto.SpecialityPatientCountDTO;
import com.challenge.challenge.service.RecentCommandsService;
import com.challenge.challenge.service.SpecialityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get top specialties by patient count")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved data",
                    content = @Content(schema = @Schema(implementation = SpecialityPatientCountDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/top")
    public ResponseEntity<List<SpecialityPatientCountDTO>> getTopSpecialities(
            @Parameter(description = "Threshold for minimum patient count to consider a specialty. Optional.", example = "5")
            @RequestParam Optional<Integer> threshold) {
        recentCommandsService.addCommand("GET /api/specialities/top");

        List<SpecialityPatientCountDTO> specialties = specialityService.getSpecialitiesWithPatientCountAboveThreshold(threshold);
        return new ResponseEntity<>(specialties, HttpStatus.OK);
    }
}