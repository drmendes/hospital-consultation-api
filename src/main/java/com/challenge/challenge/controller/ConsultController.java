package com.challenge.challenge.controller;

import com.challenge.challenge.model.Consult;
import com.challenge.challenge.model.dto.ConsultCreationDTO;
import com.challenge.challenge.model.dto.ConsultInfoDTO;
import com.challenge.challenge.model.dto.ConsultResponseDTO;
import com.challenge.challenge.service.ConsultService;
import com.challenge.challenge.service.RecentCommandsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consults")
public class ConsultController {

    @Autowired
    private ConsultService consultService;

    @Autowired
    private RecentCommandsService recentCommandsService;

    @Autowired
    public ConsultController(ConsultService consultService) {
        this.consultService = consultService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new consult.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Successfully created consult.",
                    content = @Content(schema = @Schema(implementation = ConsultResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data.")
    })
    public ConsultResponseDTO createConsult(@RequestBody ConsultCreationDTO consultCreationDTO) {
        recentCommandsService.addCommand("POST /api/consults");
        return consultService.createConsult(consultCreationDTO);
    }


    @GetMapping
    public ResponseEntity<List<Consult>> getAllConsults() {
        recentCommandsService.addCommand("GET /api/consults");
        return ResponseEntity.ok(consultService.getAllConsults());
    }

    @GetMapping("/info")
    public ResponseEntity<List<ConsultInfoDTO>> getAllConsultInfo() throws JsonProcessingException {
        List<ConsultInfoDTO> info = consultService.getAllConsultInfo();
        return new ResponseEntity<>(info, HttpStatus.OK);
    }

}

