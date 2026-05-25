package com.hiringms.service;

import com.hiringms.dto.JobRequest;
import com.hiringms.model.Job;
import com.hiringms.model.User;
import com.hiringms.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    public Job createJob(JobRequest request, User recruiter) {
        Job job = Job.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .location(request.getLocation())
                .company(request.getCompany())
                .salary(request.getSalary())
                .type(request.getType())
                .postedBy(recruiter)
                .build();
        return jobRepository.save(job);
    }

    public List<Job> getAllActiveJobs() {
        return jobRepository.findByActiveTrue();
    }

    public Job getJobById(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
    }

    public List<Job> getJobsByRecruiter(Long recruiterId) {
        return jobRepository.findByPostedById(recruiterId);
    }

    public Job updateJob(Long id, JobRequest request) {
        Job job = getJobById(id);
        if (request.getTitle() != null) job.setTitle(request.getTitle());
        if (request.getDescription() != null) job.setDescription(request.getDescription());
        if (request.getLocation() != null) job.setLocation(request.getLocation());
        if (request.getCompany() != null) job.setCompany(request.getCompany());
        if (request.getSalary() != null) job.setSalary(request.getSalary());
        if (request.getType() != null) job.setType(request.getType());
        return jobRepository.save(job);
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}
