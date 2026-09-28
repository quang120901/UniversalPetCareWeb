package com.springboot.universalpetcare.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.universalpetcare.exception.ResourceNotFoundException;
import com.springboot.universalpetcare.model.Appointment;
import com.springboot.universalpetcare.request.AppointmentUpdateRequest;
import com.springboot.universalpetcare.respone.ApiResponse;
import com.springboot.universalpetcare.service.appointment.AppointmentService;
import com.springboot.universalpetcare.ultis.FeedBackMessage;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import static org.springframework.http.HttpStatus.FOUND;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;



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

    @PostMapping("/book-appointment")
    public ResponseEntity<ApiResponse> bookAppointment(
        @RequestBody Appointment appointment, 
        @RequestParam Long senderId,
        @RequestParam Long recipientId) {
            try {
                Appointment theAppoinment = appointmentService.createAppointment(appointment, senderId, recipientId);
                return ResponseEntity.ok(new ApiResponse(FeedBackMessage.SUCCESS, theAppoinment));
            } catch (ResourceNotFoundException e) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
            } catch (Exception e) {
                return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
            }
    }

    @GetMapping("/appointment/{id}/appoinment")
    public ResponseEntity<ApiResponse> getAppointmentById(@PathVariable Long id) {
        try {
            Appointment appointment = appointmentService.getAppointmentById(id);
            return ResponseEntity.status(FOUND).body(new ApiResponse(FeedBackMessage.FOUND, appointment));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/appointment/{appointmentNo}/appoinment")
    public ResponseEntity<ApiResponse> getAppointmentByNo(@PathVariable String appointmentNo) {
        try {
            Appointment appointment = appointmentService.getAppointmentByNo(appointmentNo);
            return ResponseEntity.status(FOUND).body(new ApiResponse(FeedBackMessage.FOUND, appointment));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/appointment/{id}/delete")
    public ResponseEntity<ApiResponse> deleteAppointmentById(@PathVariable Long id) {
        try {
            appointmentService.deleteAppointment(id);
            return ResponseEntity.ok(new ApiResponse(FeedBackMessage.DELETE_SUCCESS, null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }  catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("appoinment/{id}/update")
    public ResponseEntity<ApiResponse> updateAppointment(
            @PathVariable Long id, 
            @RequestBody AppointmentUpdateRequest request) {
        try {
            Appointment appointment = appointmentService.updateAppointment(id, request);
            return ResponseEntity.ok(new ApiResponse(FeedBackMessage.UPDATE_SUCCESS, appointment));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(NOT_ACCEPTABLE).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
