package com.springboot.universalpetcare.service.password;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.springboot.universalpetcare.exception.ResourceNotFoundException;
import com.springboot.universalpetcare.model.User;
import com.springboot.universalpetcare.repository.UserRepository;
import com.springboot.universalpetcare.request.ChangePasswordRequest;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ChangePasswordService {
    private final UserRepository userRepository;

    public void changePassword(Long userId, ChangePasswordRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if(Objects.equals(request.getCurrentPassword(), "")
            || Objects.equals(request.getNewPassword(), "")) {
                throw new IllegalArgumentException("All fields are required");
        }
        if(!Object.equals(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Current password does not match");
        }
    }
}
