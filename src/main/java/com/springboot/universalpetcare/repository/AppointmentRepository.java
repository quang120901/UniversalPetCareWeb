package com.springboot.universalpetcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.universalpetcare.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    
}
