package com.taskforge.backend.repository;

import com.taskforge.backend.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository
        extends JpaRepository<Team, Long> {
}