package com.springboot.universalpetcare.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.universalpetcare.model.Appointment;
import com.springboot.universalpetcare.respone.ApiResponse;
import com.springboot.universalpetcare.service.appointment.AppointmentService;
import com.springboot.universalpetcare.ultis.FeedBackMessage;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import static org.springframework.http.HttpStatus.FOUND;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController 
@RequiredArgsConstructor 
@RequestMapping ("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping("/all")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    public ResponseEntity<ApiResponse> getAllApointments() {
        try {
            List<Appointment> appointments = appointmentService.getAllAppointments();
            return ResponseEntity.status(FOUND).body(new ApiResponse(FeedBackMessage.FOUND, appointments));

        } catch(Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
