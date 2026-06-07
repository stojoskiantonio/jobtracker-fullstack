package com.jobtracker.entity;



import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private long id;
    @Column(name="created_at", nullable = false)
    private LocalDateTime time = LocalDateTime.now();

    @Column(name="email", nullable = false)
    @Email(message = "Invalid Email Format")
    @NotBlank(message = "This field cannot be blank")
    private String email;
    @Column(name="first_name", nullable = false)
    @NotBlank(message = "This field cannot be blank")
    private String firstName;
    @Column(name="last_name", nullable = false)
    @NotBlank(message = "This field cannot be blank")
    private String lastName;
    @Column(name="password", nullable = false)
    @Size(min = 8, message = "Password has to contain 8 characters or more")
    @NotBlank(message = "This field cannot be blank")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name="role", nullable = false)
    private Role role;

    public User() {

    }

    public User (String email, String firstName, String lastName, String password, Role role) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
