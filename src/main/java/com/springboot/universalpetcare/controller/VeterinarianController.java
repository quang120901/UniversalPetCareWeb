package com.springboot.universalpetcare.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
