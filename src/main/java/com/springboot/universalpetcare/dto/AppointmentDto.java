package com.springboot.universalpetcare.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.springboot.universalpetcare.enums.AppointmentStatus;

import lombok.Data;

@Data 
public class AppointmentDto {
    private Long id;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private LocalDate createdAt;
    private String reason;
    private AppointmentStatus status;
    private String appointmentNo;
    private UserDto patient;
    private UserDto veterinary;
    private List<PetDto> pets;
}
