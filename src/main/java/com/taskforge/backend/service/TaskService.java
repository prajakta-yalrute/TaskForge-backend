package com.taskforge.backend.service;

import com.taskforge.backend.dto.TaskRequest;
import com.taskforge.backend.entity.Project;
import com.taskforge.backend.entity.Task;
import com.taskforge.backend.entity.TaskStatus;
import com.taskforge.backend.entity.User;
import com.taskforge.backend.repository.ProjectRepository;
import com.taskforge.backend.repository.TaskRepository;
import com.taskforge.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    // Create Task
    public Task createTask(TaskRequest request) {

        Project project = projectRepository.findById(
                request.getProjectId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Project not found"));

        User user = userRepository.findById(
                request.getAssignedUserId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found"));

        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(request.getStatus())
                .project(project)
                .assignedUser(user)
                .build();

        return taskRepository.save(task);
    }

    // Get all tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Update task status
    public Task updateTaskStatus(
            Long taskId,
            TaskStatus status) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Task not found"));

        task.setStatus(status);

        return taskRepository.save(task);
    }

    // Search by status
    public List<Task> getTasksByStatus(
            TaskStatus status) {

        return taskRepository.findByStatus(status);
    }

    // Filter by project
    public List<Task> getTasksByProject(
            Long projectId) {

        return taskRepository.findByProjectId(projectId);
    }
}