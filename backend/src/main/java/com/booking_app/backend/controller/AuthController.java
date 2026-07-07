package com.booking_app.backend.controller;
import org.springframework.web.bind.annotation.*;

import com.booking_app.backend.dto.AuthResponse;
import com.booking_app.backend.dto.LoginRequest;
import com.booking_app.backend.dto.RegisterRequest;
import com.booking_app.backend.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}