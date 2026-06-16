package com.taskforge.backend.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping("/upload")
public ResponseEntity<String> uploadFile(
        @RequestParam("file") MultipartFile file)
        throws IOException {

    File directory = new File(uploadDir);

    if (!directory.exists()) {
        directory.mkdirs();
    }

    String fileName =
            UUID.randomUUID() + "_"
                    + file.getOriginalFilename();

    File destination = new File(
            directory.getAbsolutePath(),
            fileName);

    file.transferTo(destination);

    return ResponseEntity.ok(
            "Uploaded: " + fileName);
}
}