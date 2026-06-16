package com.taskforge.backend.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping
    public String adminAccess() {
        return "Welcome Admin!";
    }
}