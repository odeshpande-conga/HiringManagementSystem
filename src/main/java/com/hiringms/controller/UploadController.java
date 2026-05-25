package com.hiringms.controller;

import com.hiringms.dto.ApiResponse;
import com.hiringms.model.User;
import com.hiringms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
public class UploadController {

    private final UserRepository userRepository;

    // API 18: Upload Resume - POST /api/upload/resume
    @PostMapping("/resume")
    public ResponseEntity<ApiResponse> uploadResume(Authentication authentication,
                                                     @RequestParam("file") MultipartFile file) {
        try {
            User user = (User) authentication.getPrincipal();
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path uploadDir = Paths.get("uploads/resumes");
            Files.createDirectories(uploadDir);
            Path filePath = uploadDir.resolve(fileName);
            Files.write(filePath, file.getBytes());

            user.setResumeUrl(filePath.toString());
            userRepository.save(user);

            return ResponseEntity.ok(new ApiResponse(true, "Resume uploaded", filePath.toString()));
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Upload failed: " + e.getMessage()));
        }
    }
}

