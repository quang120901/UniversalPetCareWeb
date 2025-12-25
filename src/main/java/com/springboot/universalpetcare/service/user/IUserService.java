package com.springboot.universalpetcare.service.user;

import com.springboot.universalpetcare.model.User;
import com.springboot.universalpetcare.request.RegistrationRequest;
import com.springboot.universalpetcare.request.UserUpdateRequest;

public interface IUserService {
    User register(RegistrationRequest request);
    
    User update(Long userId, UserUpdateRequest request);
}
