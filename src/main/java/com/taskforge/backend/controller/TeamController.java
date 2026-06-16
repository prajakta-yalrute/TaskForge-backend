package com.taskforge.backend.controller;

import com.taskforge.backend.dto.TeamRequest;
import com.taskforge.backend.entity.Team;
import com.taskforge.backend.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public Team createTeam(@RequestBody TeamRequest request) {
        return teamService.createTeam(request);
    }

    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }
}