package com.springboot.universalpetcare.service.appointment;

import java.util.List;

import com.springboot.universalpetcare.model.Appointment;
import com.springboot.universalpetcare.request.AppointmentUpdateRequest;
import com.springboot.universalpetcare.request.BookAppointmentRequest;

public interface IAppointmentService {
    Appointment createAppointment(BookAppointmentRequest appointment, Long sender, Long recipient);
    List<Appointment> getAllAppointments();
    Appointment updateAppointment(Long id, AppointmentUpdateRequest request);

    void deleteAppointment(Long id);
    Appointment getAppointmentById(Long id);
    Appointment getAppointmentByNo(String appointmentNo);
}
