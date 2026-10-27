package com.springboot.universalpetcare.service.veterinarian;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.springboot.universalpetcare.dto.UserDto;
import com.springboot.universalpetcare.model.Veterinarian;

public interface IVeterinarianService {
    List<UserDto> getAllVeterinariansWithDetails();

    List<UserDto> findAvailableVetsForAppointment(String specialization, LocalDate date, LocalTime time);

    List<Veterinarian> getVeterinariansBySpecialization(String specialization);
}
