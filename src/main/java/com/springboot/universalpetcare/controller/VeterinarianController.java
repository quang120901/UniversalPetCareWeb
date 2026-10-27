package com.springboot.universalpetcare.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.universalpetcare.dto.UserDto;
import com.springboot.universalpetcare.respone.ApiResponse;
import com.springboot.universalpetcare.service.veterinarian.IVeterinarianService;
import com.springboot.universalpetcare.ultis.FeedBackMessage;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import com.springboot.universalpetcare.ultis.UrlMapping;


@RestController 
@RequestMapping(UrlMapping.VETERINARIANS) 
@RequiredArgsConstructor 
public class VeterinarianController {
    private final IVeterinarianService veterinarianService;

    @GetMapping(UrlMapping.GET_ALL_VETERINARIANS)
    public ResponseEntity<ApiResponse> getAllVeterinarians() {
        List<UserDto> allVeterinarians = veterinarianService.getAllVeterinariansWithDetails();
        return ResponseEntity.ok(new ApiResponse(FeedBackMessage.RESOURCE_FOUND, allVeterinarians));
    }

    @GetMapping(UrlMapping.SEARCH_VERINARIAN_FOR_APPOINTMENT)
    public ResponseEntity<ApiResponse> searchVeterinariansForAppointment(
            @RequestParam(required = false) LocalDate date,
            @RequestParam(required = false) LocalTime time,
            @RequestParam String specialization) {
        List<UserDto> availableVeterinarians = veterinarianService.findAvailableVetsForAppointment(specialization, date, time);
        if(availableVeterinarians.isEmpty()) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(FeedBackMessage.NO_VETS_AVAILABLE, null));
        }
        return ResponseEntity.ok(new ApiResponse(FeedBackMessage.RESOURCE_FOUND, availableVeterinarians));
    }
}
