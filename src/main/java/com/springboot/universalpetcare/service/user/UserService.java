package com.springboot.universalpetcare.service.user;

import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties.Apiversion.Use;
import org.springframework.stereotype.Service;

import com.springboot.universalpetcare.factory.UserFactory;
import com.springboot.universalpetcare.factory.VeterinarianFactory;
import com.springboot.universalpetcare.model.User;
import com.springboot.universalpetcare.repository.UserRepository;
import com.springboot.universalpetcare.request.RegistrationRequest;
import com.springboot.universalpetcare.request.UserUpdateRequest;

import com.springboot.universalpetcare.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final UserFactory userFactory;
    private final VeterinarianFactory veterinarianFactory;

    @Override
    public User register(RegistrationRequest request) {
        return userFactory.createUser(request);
    }

    @Override
    public User update(Long userId, UserUpdateRequest request) {
        User user = findById(userId);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setGender(request.getGender());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setSpecialization(request.getSpecialization());

        return userRepository.save(user);
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
