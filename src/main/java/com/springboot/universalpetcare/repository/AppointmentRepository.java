package com.springboot.universalpetcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.universalpetcare.enums.AppointmentStatus;
import com.springboot.universalpetcare.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Appointment findByAppointmentNo(String appointmentNo);

    boolean existsByVeterinarianIdAndPatientIdAndStatus(Long veterinarianId, Long reviewerId,
            AppointmentStatus appointmentStatus);
    
    List<Appointment> findAllByUserId(Long userId);
}
