package com.hiringms.service;

import com.hiringms.dto.ApplicationRequest;
import com.hiringms.model.Application;
import com.hiringms.model.Job;
import com.hiringms.model.User;
import com.hiringms.repository.ApplicationRepository;
import com.hiringms.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;

    public Application applyForJob(ApplicationRequest request, User candidate) {
        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new RuntimeException("Job not found"));

        Application application = Application.builder()
                .job(job)
                .candidate(candidate)
                .coverLetter(request.getCoverLetter())
                .status(Application.Status.PENDING)
                .build();

        return applicationRepository.save(application);
    }

    public List<Application> getMyApplications(User candidate) {
        return applicationRepository.findByCandidateId(candidate.getId());
    }

    public List<Application> getApplicantsForJob(Long jobId) {
        return applicationRepository.findByJobId(jobId);
    }

    public Application updateStatus(Long id, String status) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        application.setStatus(Application.Status.valueOf(status.toUpperCase()));
        return applicationRepository.save(application);
    }
}

