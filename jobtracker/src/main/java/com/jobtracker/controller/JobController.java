package com.jobtracker.controller;

import com.jobtracker.dto.JobApplicationDTO;
import com.jobtracker.entity.JobApplication;
import com.jobtracker.entity.User;
import com.jobtracker.exception.ResourceNotFoundException;
import com.jobtracker.exception.UnauthorizedException;
import com.jobtracker.service.JobApplicationService;
import com.jobtracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    private final JobApplicationService jobService;
    private final UserService userService;

    public JobController(JobApplicationService jobService, UserService userService) {
        this.jobService = jobService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<JobApplicationDTO> create(@Valid @RequestBody JobApplication jobApplication) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        jobApplication.setUser(user);
        JobApplication saved = jobService.newJob(jobApplication);
        return ResponseEntity.ok(toDTO(saved));
    }

    @GetMapping
    public ResponseEntity<List<JobApplicationDTO>> findAll() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("No users found"));
        List<JobApplicationDTO> jobs = jobService.findByUserId(user.getId())
                .stream().map(this::toDTO).toList();
        return ResponseEntity.ok(jobs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobApplicationDTO> findById(@PathVariable long id) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        JobApplication job = jobService.findById(id)
                .orElseThrow(() -> new UnauthorizedException("User not authorized"));

        return ResponseEntity.ok(toDTO(job));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        JobApplication job = jobService.findById(id)
                        .orElseThrow(() -> new UnauthorizedException("No job found with that ID"));

        if (job.getUser().getId() != user.getId()) {
            throw new UnauthorizedException("You don't have access to delete this job");
        }

        jobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }

    private JobApplicationDTO toDTO(JobApplication job) {
        return new JobApplicationDTO(job.getId(), job.getCompanyName(), job.getPosition(),
                job.getStatus(), job.getAppliedAt(), job.getUser().getId());
    }
}