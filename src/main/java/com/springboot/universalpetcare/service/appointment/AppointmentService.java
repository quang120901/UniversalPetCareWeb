package com.springboot.universalpetcare.service.appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springboot.universalpetcare.enums.AppointmentStatus;
import com.springboot.universalpetcare.exception.ResourceNotFoundException;
import com.springboot.universalpetcare.model.Appointment;
import com.springboot.universalpetcare.model.User;
import com.springboot.universalpetcare.repository.AppointmentRepository;
import com.springboot.universalpetcare.repository.UserRepository;
import com.springboot.universalpetcare.request.AppointmentRequest;
import com.springboot.universalpetcare.ultis.FeedBackMessage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class AppointmentService implements IAppointmentService{

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;

    @Override
    public Appointment createAppointment(Appointment appointment, Long senderId, Long recipientId) {
        Optional<User> sender = userRepository.findById(senderId);
        Optional<User> recipient = userRepository.findById(recipientId);
        if(sender.isPresent() && recipient.isPresent()) {
            appointment.setPatient(sender.get());
            appointment.setVeterinarian(recipient.get());
            appointment.setAppointmentNo();
            appointment.setStatus(AppointmentStatus.WAITING_FOR_APPROVAL);
            return appointmentRepository.save(appointment);
        }
        throw new ResourceNotFoundException("sender or recipient not found");
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment updateAppointment(Long id, AppointmentRequest request) {
        return null;
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.findById(id)
                .ifPresent(appointment -> {appointmentRepository.delete(appointment); });;
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("appointment not found"));
    }

    @Override
    public Appointment getAppointmentByNo(String appointmentNo) {
        return appointmentRepository.findByAppointmentNo(appointmentNo);
    } 
}
