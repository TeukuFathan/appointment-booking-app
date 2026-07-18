package com.booking_app.backend.service;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.booking_app.backend.dto.UserResponse;
import com.booking_app.backend.entity.User;
import com.booking_app.backend.repository.UserRepository;

@Service
public class UserService {
    
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    
    public UserResponse findUserByEmail(String email){
        User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("User not found"));;
        
        return new UserResponse(
            user.getName(),
            user.getEmail(),
            user.getBusinessName(),
            user.getBusinessSlug()
        );
    }
}
