package com.springboot.universalpetcare.service.appointment;

import java.util.List;

import com.springboot.universalpetcare.model.Appointment;
import com.springboot.universalpetcare.request.AppointmentUpdateRequest;

public interface IAppointmentService {
    Appointment createAppointment(Appointment appointment, Long sender, Long recipient);
    List<Appointment> getAllAppointments();
    Appointment updateAppointment(Long id, AppointmentUpdateRequest request);
    void deleteAppointment(Long id);
    Appointment getAppointmentById(Long id);
    Appointment getAppointmentByNo(String appointmentNo);
}
