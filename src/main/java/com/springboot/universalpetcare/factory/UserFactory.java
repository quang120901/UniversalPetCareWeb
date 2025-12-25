package com.springboot.universalpetcare.factory;

import com.springboot.universalpetcare.model.User;
import com.springboot.universalpetcare.request.RegistrationRequest;

public interface UserFactory {
    public User createUser(RegistrationRequest registrationRequest);
}
