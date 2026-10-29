package com.springboot.universalpetcare.service.veterinarian;

import com.springboot.universalpetcare.repository.AppointmentRepository;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.universalpetcare.dto.EntityConverter;
import com.springboot.universalpetcare.dto.UserDto;
import com.springboot.universalpetcare.exception.ResourceNotFoundException;
import com.springboot.universalpetcare.model.Appointment;
import com.springboot.universalpetcare.model.Veterinarian;
import com.springboot.universalpetcare.repository.ReviewRepository;
import com.springboot.universalpetcare.repository.UserRepository;
import com.springboot.universalpetcare.repository.VeterinarianRepository;
import com.springboot.universalpetcare.service.photo.PhotoService;
import com.springboot.universalpetcare.service.review.ReviewService;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class VeterinarianService implements IVeterinarianService{
    private final AppointmentRepository appointmentRepository;
    private final VeterinarianRepository veterinarianRepository;
    private final EntityConverter<Veterinarian, UserDto> entityConverter;
    private final ReviewService reviewService;
    private final PhotoService photoService;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    @Override 
    public List<UserDto> getAllVeterinariansWithDetails() {
        List<Veterinarian> veterinarians = userRepository.findAllByUserType("VET");
        return veterinarians.stream()
            .map(this::mapVeterinarianToUserDto)
            .toList();
    }

    @Override 
    public List<UserDto> findAvailableVetsForAppointment(String specialization, LocalDate date, LocalTime time) {
        List<Veterinarian> filteredVets = getAvailableVeterinarians(specialization, date, time);
        return filteredVets.stream()
                .map(this :: mapVeterinarianToUserDto)
                .toList();
    }

    @Override 
    public List<Veterinarian> getVeterinariansBySpecialization(String specialization) {
        if(!veterinarianRepository.existsBySpecialization(specialization)) {
            throw new ResourceNotFoundException("No veterinarian found with " + specialization + " in the system");
        }
        return veterinarianRepository.findBySpecialization(specialization);
    }

    private UserDto mapVeterinarianToUserDto(Veterinarian veterinarian) {
        UserDto userDto = entityConverter.mapEntityToDto(veterinarian, UserDto.class);
        double averageRating = reviewService.getAverageRatingForVet(veterinarian.getId());
        Long totalReviewer = reviewRepository.countByVeterinarianId(veterinarian.getId());
        userDto.setAverageRating(averageRating);
        userDto.setTotalReviewers(totalReviewer);
        if(veterinarian.getPhoto() != null) {
            try {
                byte[] photoBytes = photoService.getImageData(veterinarian.getPhoto().getId());
                userDto.setPhoto(photoBytes);
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return userDto;
    }

    private List<Veterinarian> getAvailableVeterinarians(String specialization, LocalDate date, LocalTime time) {
        List<Veterinarian> veterinarians = getVeterinariansBySpecialization(specialization);
        return veterinarians.stream()
                .filter(vet -> isVetAvailable(vet, date, time))
                .toList();

    }

    private boolean isVetAvailable(Veterinarian veterinarian, LocalDate requestedDate, LocalTime requestedTime) {
        if(requestedDate != null && requestedTime != null) {
            LocalTime requestedEndTime = requestedTime.plusHours(2);
            return appointmentRepository.findByVeterinarianAndAppointmentDate(veterinarian, requestedDate)
                .stream()
                .noneMatch(existingAppointment -> doesAppointmentOverLap(existingAppointment, requestedTime, requestedEndTime));
        }
        return true;
    }

    private boolean doesAppointmentOverLap(Appointment existingAppointment, LocalTime requestedStartTime, LocalTime requestedEbdTime) {
        LocalTime existingStartTime = existingAppointment.getAppointmentTime();
        LocalTime existingEndTime = existingStartTime.plusHours(2);
        LocalTime unavailableStartTime = existingStartTime.minusHours(1);
        LocalTime unavailableEndTime = existingEndTime.plusMinutes(170);
        return !requestedStartTime.isBefore(unavailableStartTime) && !requestedEbdTime.isAfter(unavailableEndTime);
    }
}
