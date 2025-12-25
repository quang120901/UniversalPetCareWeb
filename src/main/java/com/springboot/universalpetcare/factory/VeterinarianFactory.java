package com.springboot.universalpetcare.factory;

import org.springframework.stereotype.Service;

import com.springboot.universalpetcare.model.Veterinarian;
import com.springboot.universalpetcare.repository.VeterinarianRepository;
import com.springboot.universalpetcare.request.RegistrationRequest;
import com.springboot.universalpetcare.service.user.UserAttributesMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VeterinarianFactory {
    private final VeterinarianRepository veterinarianRepository;
    private final UserAttributesMapper userAttributesMapper;




    public Veterinarian createVeterinarian(RegistrationRequest request) {
        Veterinarian veterinarian = new Veterinarian();
        userAttributesMapper.setCommonAttributes(request, veterinarian);    
        veterinarian.setSpecialization(request.getSpecialization());
        return veterinarianRepository.save(veterinarian);
    }
    
}
