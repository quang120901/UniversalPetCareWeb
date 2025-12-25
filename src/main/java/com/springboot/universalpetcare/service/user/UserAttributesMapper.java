package com.springboot.universalpetcare.service.user;

import org.springframework.stereotype.Component;

import com.springboot.universalpetcare.model.User;
import com.springboot.universalpetcare.request.RegistrationRequest;

@Component
public class UserAttributesMapper {
    public void setCommonAttributes(RegistrationRequest source, User target) {
        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        target.setGender(source.getGender());
        target.setPhoneNumber(source.getPhoneNumber());
        target.setEmail(source.getEmail());
        target.setPassword(source.getPassword());
        target.setEnabled(source.isEnabled());
        target.setUserType(source.getUserType());
    }
}
