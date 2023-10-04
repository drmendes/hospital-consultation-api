package com.challenge.challenge.controller;

import com.challenge.challenge.model.Consult;
import com.challenge.challenge.model.dto.ConsultCreationDTO;
import com.challenge.challenge.model.dto.ConsultInfoDTO;
import com.challenge.challenge.model.dto.ConsultResponseDTO;
import com.challenge.challenge.service.ConsultService;
import com.challenge.challenge.service.RecentCommandsService;
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
    public ResponseEntity<List<ConsultInfoDTO>> getAllConsultInfo() {
        List<ConsultInfoDTO> info = consultService.getAllConsultInfo();
        return new ResponseEntity<>(info, HttpStatus.OK);
    }

}

