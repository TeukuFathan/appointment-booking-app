package com.booking_app.backend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.booking_app.backend.dto.AuthResponse;
import com.booking_app.backend.dto.LoginRequest;
import com.booking_app.backend.dto.RegisterRequest;
import com.booking_app.backend.entity.User;
import com.booking_app.backend.repository.UserRepository;

@Service
public class AuthService {
    // Service that take care of the authentification 
    // can the user login ? register ?

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse register(RegisterRequest request) {
        // check if the email already being use
        if (userRepository.existsByEmail(request.getEmail())) {
            return new AuthResponse("Email already exists");
        }

        // check if the busines name already exist 
        if (userRepository.existsByBusinessSlug(request.getBusinessSlug())) {
            return new AuthResponse("Business slug already exists");
        }

        // maybe add somekind of email authentification part ?
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setBusinessName(request.getBusinessName());
        user.setBusinessSlug(request.getBusinessSlug());

        userRepository.save(user);

        return new AuthResponse("User registered successfully");
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (user == null) {
            return new AuthResponse("Invalid email or password");
        }

        boolean passwordMatches = passwordEncoder.matches(
                request.getPassword(),
                user.getPasswordHash()
        );

        if (!passwordMatches) {
            return new AuthResponse("Invalid email or password");
        }

        return new AuthResponse("Login successful");
    }
}