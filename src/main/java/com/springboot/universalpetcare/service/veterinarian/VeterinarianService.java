package com.springboot.universalpetcare.service.veterinarian;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.universalpetcare.dto.EntityConverter;
import com.springboot.universalpetcare.dto.UserDto;
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
}
