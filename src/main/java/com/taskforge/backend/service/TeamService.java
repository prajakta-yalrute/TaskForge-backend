package com.taskforge.backend.service;

import com.taskforge.backend.dto.TeamRequest;
import com.taskforge.backend.entity.Team;
import com.taskforge.backend.entity.User;
import com.taskforge.backend.repository.TeamRepository;
import com.taskforge.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    public Team createTeam(TeamRequest request) {

        List<User> users =
                userRepository.findAllById(
                        request.getUserIds());

        Team team = Team.builder()
                .name(request.getName())
                .users(users)
                .build();

        return teamRepository.save(team);
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }
}