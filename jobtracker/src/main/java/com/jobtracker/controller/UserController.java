package com.jobtracker.controller;

import com.jobtracker.dto.UserDTO;
import com.jobtracker.entity.User;
import com.jobtracker.exception.ResourceNotFoundException;
import com.jobtracker.security.JwtUtil;
import com.jobtracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User saved = userService.register(user);
        return ResponseEntity.ok(new UserDTO(saved.getId(), saved.getFirstName(), saved.getLastName(), saved.getEmail(),
                saved.getRole()));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody Map<String, String> body) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(body.get("email"), body.get("password"))
        );
        String token = jwtUtil.generateToken(body.get("email"));
        return ResponseEntity.ok(Map.of("token", token));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable long id) {
        return userService.findById(id)
                .map(user -> new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail()
                        ,user.getRole()))
                .map(ResponseEntity::ok)
                .orElseThrow(()-> new ResourceNotFoundException("No user found with that ID"));
    }
}
