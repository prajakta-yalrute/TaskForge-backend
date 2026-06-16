package com.taskforge.backend.dto;

import com.taskforge.backend.entity.TaskStatus;
import lombok.Data;

@Data
public class TaskRequest {

    private String title;
    private String description;
    private TaskStatus status;
    private Long projectId;
    private Long assignedUserId;
}