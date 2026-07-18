package com.booking_app.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booking_app.backend.dto.UserResponse;
import com.booking_app.backend.entity.User;
import com.booking_app.backend.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public UserResponse getUserByEmail(@RequestParam String email){
        return this.userService.findUserByEmail(email);
    }
}
