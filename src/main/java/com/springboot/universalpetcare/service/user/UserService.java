package com.springboot.universalpetcare.service.user;

import com.springboot.universalpetcare.service.photo.PhotoService;
import com.springboot.universalpetcare.service.review.IReviewService;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.springboot.universalpetcare.factory.UserFactory;
import com.springboot.universalpetcare.factory.VeterinarianFactory;
import com.springboot.universalpetcare.model.Review;
import com.springboot.universalpetcare.model.User;
import com.springboot.universalpetcare.repository.UserRepository;
import com.springboot.universalpetcare.request.RegistrationRequest;
import com.springboot.universalpetcare.request.UserUpdateRequest;
import com.springboot.universalpetcare.service.appointment.AppointmentService;
import com.springboot.universalpetcare.service.pet.IPetService;
import com.springboot.universalpetcare.ultis.FeedBackMessage;
import com.springboot.universalpetcare.dto.AppointmentDto;
import com.springboot.universalpetcare.dto.EntityConverter;
import com.springboot.universalpetcare.dto.ReviewDto;
import com.springboot.universalpetcare.dto.UserDto;
import com.springboot.universalpetcare.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final PhotoService photoService;
    private final UserRepository userRepository;
    private final UserFactory userFactory;
    private final VeterinarianFactory veterinarianFactory;
    private final EntityConverter<User, UserDto> entityConverter;
    private final AppointmentService appointmentService;
    private final ModelMapper modelMapper;
    private final IPetService petService;
    private final IReviewService reviewService;

    @Override
    public User register(RegistrationRequest request) {
        return userFactory.createUser(request);
    }

    @Override
    public User update(Long userId, UserUpdateRequest request) {
        User user = findById(userId);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setGender(request.getGender());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setSpecialization(request.getSpecialization());

        return userRepository.save(user);
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(FeedBackMessage.RESOURCE_NOT_FOUND));
    }

    @Override
    public void delete(Long userId) {
        userRepository.findById(userId)
            .ifPresentOrElse(userRepository::delete, () -> {
                throw new ResourceNotFoundException(FeedBackMessage.RESOURCE_NOT_FOUND);
            });
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> entityConverter.mapEntityToDto(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override 
    public UserDto getUserWithDetails(Long userId) throws SQLException{
        //1. Get the user
        User user = findById(userId);
        //2. Convert the user to a userDto
        UserDto userDto = entityConverter.mapEntityToDto(user, UserDto.class);
        //3. Get user appointments (users(patient and a vet))
        setUserAppointment(userDto);
        setUserPhoto(userDto, user);
        setUserReviews(userDto, userId);
        return userDto;
    }

    private void setUserAppointment(UserDto userDto) {
        List<AppointmentDto> appointments = appointmentService.getUserAppointments(userDto.getId());
        userDto.setAppointments(appointments);
    }

    private void setUserPhoto(UserDto userDto, User user) throws SQLException {
        if(user.getPhoto() != null) {
            userDto.setPhotoId(user.getPhoto().getId());
            userDto.setPhoto(photoService.getImageData(user.getPhoto().getId()));
        }
    }

    @SneakyThrows 
    private void setUserReviews(UserDto userDto, Long userId) {
        Page<Review> reviewPage = reviewService.findAllReviewsByUserId(userId, 0, Integer.MAX_VALUE);
        List<ReviewDto> reviewDto = reviewPage.getContent()
                .stream()
                .map(this::mapReviewToDto).toList();
        if(!reviewDto.isEmpty()) {
            double averageRating = reviewService.getAverageRatingForVet(userId);
        }
        userDto.setReviews(reviewDto);
    }

    private ReviewDto mapReviewToDto(Review review) throws SQLException {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setStars(review.getStars());
        reviewDto.setFeedback(review.getFeedback());
        mapVeterinarianInfo(reviewDto, review);
        mapPatientInfo(reviewDto, reviewDto);
        return reviewDto;
    }

    private void mapVeterinarianInfo(ReviewDto reviewDto, Review review) throws SQLException{
        if(review.getVeterinarian() != null) {
            reviewDto.setVetId(review.getVeterinarian().getId());
            reviewDto.setVetName(review.getVeterinarian().getFirstName()+ "" + review.getVeterinarian().getLastName());
            setVeterinarianPhoto(reviewDto, review);
        }
    }

    private void mapPatientInfo(ReviewDto reviewDto, Review review) throws SQLException{
        if(review.getPatient() != null) {
            reviewDto.setReviewerId(review.getPatient().getId());
            reviewDto.setReviewerName(review.getPatient().getFirstName()+ "" + review.getPatient().getLastName());
            setReviewerPhoto(reviewDto, review);
        }
    }

    private void setReviewerPhoto(ReviewDto reviewDto, Review review) throws SQLException{
        if(review.getPatient().getPhoto() != null) {
            reviewDto.setReviewerImage(photoService.getImageData(review.getPatient().getPhoto().getId()));
        } else {
            reviewDto.setReviewerImage(null);
        }
    }

    private void setVeterinarianPhoto(ReviewDto reviewDto, Review review) throws SQLException{
        if(review.getVeterinarian().getPhoto() != null) {
            reviewDto.setVetImage(photoService.getImageData(review.getVeterinarian().getPhoto().getId()));
        } else {
            reviewDto.setVetImage(null);
        }
    }
}