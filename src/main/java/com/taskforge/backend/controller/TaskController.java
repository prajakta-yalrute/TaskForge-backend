package com.taskforge.backend.controller;

import com.taskforge.backend.dto.TaskRequest;
import com.taskforge.backend.entity.Task;
import com.taskforge.backend.entity.TaskStatus;
import com.taskforge.backend.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public Task createTask(
            @RequestBody TaskRequest request) {

        return taskService.createTask(request);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PutMapping("/{id}/status")
    public Task updateStatus(
            @PathVariable Long id,
            @RequestParam TaskStatus status) {

        return taskService.updateTaskStatus(
                id, status);
    }

    @GetMapping("/status/{status}")
    public List<Task> getByStatus(
            @PathVariable TaskStatus status) {

        return taskService.getTasksByStatus(status);
    }

    @GetMapping("/project/{projectId}")
    public List<Task> getByProject(
            @PathVariable Long projectId) {

        return taskService.getTasksByProject(
                projectId);
    }
}