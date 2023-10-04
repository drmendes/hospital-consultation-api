package com.challenge.challenge.service;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecentCommandsService {

    private static final int MAX_COMMANDS = 10;  // Store last 10 commands
    private final List<String> recentCommands = new ArrayList<>();

    public void addCommand(String command) {
        if (recentCommands.size() >= MAX_COMMANDS) {
            recentCommands.remove(0);
        }
        recentCommands.add(command);
    }

    public List<String> getRecentCommands() {
        return recentCommands;
    }
}


