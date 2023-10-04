package com.challenge.challenge.controller;

import com.challenge.challenge.service.RecentCommandsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recent-commands")
public class RecentCommandsController {

    @Autowired
    private RecentCommandsService recentCommandsService;

    @GetMapping
    public ResponseEntity<List<String>> getRecentCommands() {
        return ResponseEntity.ok(recentCommandsService.getRecentCommands());
    }
}
