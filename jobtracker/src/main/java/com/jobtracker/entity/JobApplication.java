package com.jobtracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.*;

@Entity
@Table(name="job_applications")
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private long id;
    @Column(name="company_name", nullable = false)
    @NotBlank(message = "Company name cannot be blank")
    private String companyName;
    @Column(name="position", nullable = false)
    @NotBlank(message = "Position cannot be blank")
    private String position;
    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable = false)
    @NotNull(message = "Status is required")
    private Status status;
    @Column(name="applied_at", nullable = false)
    private LocalDateTime appliedAt;

    @PrePersist
    protected void onCreate() {
        if (appliedAt == null) {
            appliedAt = LocalDateTime.now();
        }
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public JobApplication() {

    }

    public JobApplication(String companyName, String position, Status status, LocalDateTime appliedAt, User user) {
        this.companyName = companyName;
        this.position = position;
        this.status = status;
        this.appliedAt = appliedAt;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
