package com.taskforge.backend.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {

    @GetMapping
    public String managerAccess() {
        return "Welcome Manager!";
    }
}