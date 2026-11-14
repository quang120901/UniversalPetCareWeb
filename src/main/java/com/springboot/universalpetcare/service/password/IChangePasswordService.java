package com.springboot.universalpetcare.service.password;

import com.springboot.universalpetcare.request.ChangePasswordRequest;

public interface IChangePasswordService {

    void changePassword(Long userId, ChangePasswordRequest request);
    
}
