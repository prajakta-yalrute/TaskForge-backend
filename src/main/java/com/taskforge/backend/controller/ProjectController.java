package com.taskforge.backend.controller;

import com.taskforge.backend.dto.ProjectRequest;
import com.taskforge.backend.entity.Project;
import com.taskforge.backend.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public Project createProject(
            @RequestBody ProjectRequest request) {

        return projectService.createProject(request);
    }

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public Project getProjectById(
            @PathVariable Long id) {

        return projectService.getProjectById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteProject(
            @PathVariable Long id) {

        projectService.deleteProject(id);
        return "Project deleted successfully";
    }
}