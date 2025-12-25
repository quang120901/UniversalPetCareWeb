package com.springboot.universalpetcare.factory;

import org.springframework.stereotype.Service;

import com.springboot.universalpetcare.model.Patient;
import com.springboot.universalpetcare.repository.PatientRepository;
import com.springboot.universalpetcare.request.RegistrationRequest;
import com.springboot.universalpetcare.service.user.UserAttributesMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientFactory {
    private final PatientRepository patientRepository;
    private final UserAttributesMapper userAttributesMapper;

    public Patient createPatient(RegistrationRequest request) {
        Patient patient = new Patient();
        userAttributesMapper.setCommonAttributes(request, patient);
        return patientRepository.save(patient);
    }
}