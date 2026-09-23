package com.springboot.universalpetcare.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Appointment {
    private Long id;
    private String reason;
    private LocalDate date;
    private String appointmentNo;
    private LocalDate createdAt;
    private User patient;
    private User recipient;
    
}
