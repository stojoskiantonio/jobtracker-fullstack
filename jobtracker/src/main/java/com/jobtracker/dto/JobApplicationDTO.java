package com.jobtracker.dto;

import com.jobtracker.entity.Status;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;


public class JobApplicationDTO {
    private long id;
    private String companyName;
    private String position;
    private Status status;
    private LocalDateTime appliedAt = LocalDateTime.now();
    private long userId;

    public JobApplicationDTO() {

    }

    public JobApplicationDTO(long id, String companyName, String position, Status status, LocalDateTime appliedAt, long userId) {
        this.id = id;
        this.companyName = companyName;
        this.position = position;
        this.status = status;
        this.appliedAt = appliedAt;
        this.userId = userId;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
