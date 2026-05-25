package com.hiringms.controller;

import com.hiringms.dto.ApiResponse;
import com.hiringms.dto.JobRequest;
import com.hiringms.model.Job;
import com.hiringms.model.User;
import com.hiringms.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping
    public ResponseEntity<ApiResponse> createJob(Authentication authentication,
                                                  @Valid @RequestBody JobRequest request) {
        User recruiter = (User) authentication.getPrincipal();
        Job job = jobService.createJob(request, recruiter);
        return ResponseEntity.ok(new ApiResponse(true, "Job created successfully", job));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllJobs() {
        List<Job> jobs = jobService.getAllActiveJobs();
        return ResponseEntity.ok(new ApiResponse(true, "Jobs retrieved", jobs));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getJob(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Job retrieved", job));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateJob(@PathVariable Long id,
                                                  @RequestBody JobRequest request) {
        Job job = jobService.updateJob(id, request);
        return ResponseEntity.ok(new ApiResponse(true, "Job updated", job));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ResponseEntity.ok(new ApiResponse(true, "Job deleted"));
    }
}
