package com.springboot.universalpetcare.factory;

import org.springframework.stereotype.Service;

import com.springboot.universalpetcare.model.Admin;
import com.springboot.universalpetcare.repository.AdminRepository;
import com.springboot.universalpetcare.request.RegistrationRequest;
import com.springboot.universalpetcare.service.user.UserAttributesMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminFactory {

    private final AdminRepository adminRepository;
    private final UserAttributesMapper userAttributesMapper;


    public Admin createAdmin(RegistrationRequest request) {
        Admin admin = new Admin();
        userAttributesMapper.setCommonAttributes(request, admin);
        return adminRepository.save(admin);
    }
    
}
