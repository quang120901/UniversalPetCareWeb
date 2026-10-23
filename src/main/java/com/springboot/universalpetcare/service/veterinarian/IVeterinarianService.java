package com.springboot.universalpetcare.service.veterinarian;

import java.util.List;

import com.springboot.universalpetcare.dto.UserDto;

public interface IVeterinarianService {
    List<UserDto> getAllVeterinariansWithDetails();
}
