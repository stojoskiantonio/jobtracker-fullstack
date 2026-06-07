package com.jobtracker.service;

import com.jobtracker.entity.JobApplication;
import com.jobtracker.repository.JobApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobApplicationService {
    private final JobApplicationRepository jobRepository;

    public JobApplicationService(JobApplicationRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public JobApplication newJob (JobApplication jobApplication) {
        return jobRepository.save(jobApplication);
    }

    public void deleteJob (Long id) {
        jobRepository.deleteById(id);
    }

    public Optional<JobApplication> findById (Long userId) {
        return jobRepository.findById(userId);
    }

    public List<JobApplication> findByUserId(Long userId) {
        return jobRepository.findByUserId(userId);
    }
}
