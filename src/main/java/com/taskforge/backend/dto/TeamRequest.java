package com.taskforge.backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class TeamRequest {

    private String name;
    private List<Long> userIds;
}