package com.springboot.universalpetcare.service.user;

import org.springframework.stereotype.Service;

import com.springboot.universalpetcare.factory.UserFactory;
import com.springboot.universalpetcare.model.User;
import com.springboot.universalpetcare.repository.UserRepository;
import com.springboot.universalpetcare.request.RegistrationRequest;

import jakarta.servlet.Registration;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserFactory userFactory;

    public User add(RegistrationRequest request) {
        return userFactory.createUser(request);
    }
}
