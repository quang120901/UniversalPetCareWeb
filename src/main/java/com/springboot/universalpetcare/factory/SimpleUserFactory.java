package com.springboot.universalpetcare.factory;

import org.springframework.stereotype.Component;

import com.springboot.universalpetcare.exception.UserAlreadyExistsException;
import com.springboot.universalpetcare.model.User;
import com.springboot.universalpetcare.repository.UserRepository;
import com.springboot.universalpetcare.request.RegistrationRequest;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SimpleUserFactory implements UserFactory {
    private final UserRepository userRepository;
    private final VeterinarianFactory veterinarianFactory;
    private final PatientFactory patientFactory;
    private final AdminFactory adminFactory;

    @Override
    public User createUser(RegistrationRequest registrationRequest) {
        if(userRepository.existsByEmail(registrationRequest.getEmail())) {
            throw new UserAlreadyExistsException("User with email " + registrationRequest.getEmail() + " already exists.");
        }
        switch (registrationRequest.getUserType()) {
            case "VETERINARIAN"->{return veterinarianFactory.createVeterinarian(registrationRequest);
            }
            case "PATIENT"->{return patientFactory.createPatient(registrationRequest);
            }
            case "ADMIN"->{return adminFactory.createAdmin(registrationRequest);
            }
            default ->{
                return null;
            }
        }
    }
}

