package com.taskforge.backend.repository;

import com.taskforge.backend.entity.Task;
import com.taskforge.backend.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository
        extends JpaRepository<Task, Long> {

    List<Task> findByStatus(
            TaskStatus status);

    List<Task> findByProjectId(
            Long projectId);
}