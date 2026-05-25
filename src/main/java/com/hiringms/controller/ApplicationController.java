package com.hiringms.controller;

import com.hiringms.dto.ApiResponse;
import com.hiringms.dto.ApplicationRequest;
import com.hiringms.model.Application;
import com.hiringms.model.User;
import com.hiringms.service.ApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<ApiResponse> apply(Authentication authentication,
                                              @Valid @RequestBody ApplicationRequest request) {
        User candidate = (User) authentication.getPrincipal();
        Application application = applicationService.applyForJob(request, candidate);
        return ResponseEntity.ok(new ApiResponse(true, "Application submitted", application));
    }

    @GetMapping("/my")
    public ResponseEntity<ApiResponse> getMyApplications(Authentication authentication) {
        User candidate = (User) authentication.getPrincipal();
        List<Application> applications = applicationService.getMyApplications(candidate);
        return ResponseEntity.ok(new ApiResponse(true, "Applications retrieved", applications));
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<ApiResponse> getApplicants(@PathVariable Long jobId) {
        List<Application> applications = applicationService.getApplicantsForJob(jobId);
        return ResponseEntity.ok(new ApiResponse(true, "Applicants retrieved", applications));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse> updateStatus(@PathVariable Long id,
                                                     @RequestParam String status) {
        Application application = applicationService.updateStatus(id, status);
        return ResponseEntity.ok(new ApiResponse(true, "Status updated", application));
    }
}
