package com.springboot.universalpetcare.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNumber;
    private String email;
    private String userType;
    private boolean isEnabled;    
    private String specialization;
    private LocalDate createAt;
    private List<AppointmentDto> appointments;
    private List<ReviewDto> reviews;
    private long imageId;
    private byte[] image;
    private double averageRating;
}
