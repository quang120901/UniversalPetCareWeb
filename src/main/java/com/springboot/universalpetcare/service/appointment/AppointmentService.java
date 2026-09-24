package com.springboot.universalpetcare.service.appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.universalpetcare.exception.ResourceNotFoundException;
import com.springboot.universalpetcare.model.Appointment;
import com.springboot.universalpetcare.request.AppointmentRequest;
import com.springboot.universalpetcare.ultis.FeedBackMessage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class AppointmentService implements IAppointmentService{
    @Override
    public Appointment createAppointment(Appointment appointment, Long sender, Long recipient) {
        return null;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return List.of();
    }

    @Override
    public Appointment updateAppointment(Long id, AppointmentRequest request) {
        return null;
    }

    @Override
    public void deleteAppointment(Long id) {

    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return null;
    }

    @Override
    public Appointment getAppointmentByNo(String appointmentNo) {
        return null;
    } 
}
