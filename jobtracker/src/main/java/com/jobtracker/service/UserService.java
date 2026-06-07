package com.jobtracker.service;

import com.jobtracker.entity.User;
import com.jobtracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByEmail (String email) {
        return userRepository.findByEmail(email);
    }

}
