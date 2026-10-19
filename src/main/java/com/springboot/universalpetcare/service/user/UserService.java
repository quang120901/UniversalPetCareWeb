package com.springboot.universalpetcare.service.user;

import com.springboot.universalpetcare.service.photo.PhotoService;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.springboot.universalpetcare.factory.UserFactory;
import com.springboot.universalpetcare.factory.VeterinarianFactory;
import com.springboot.universalpetcare.model.User;
import com.springboot.universalpetcare.repository.UserRepository;
import com.springboot.universalpetcare.request.RegistrationRequest;
import com.springboot.universalpetcare.request.UserUpdateRequest;
import com.springboot.universalpetcare.service.appointment.AppointmentService;
import com.springboot.universalpetcare.service.pet.IPetService;
import com.springboot.universalpetcare.ultis.FeedBackMessage;
import com.springboot.universalpetcare.dto.AppointmentDto;
import com.springboot.universalpetcare.dto.EntityConverter;
import com.springboot.universalpetcare.dto.UserDto;
import com.springboot.universalpetcare.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;

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

    public UserDto getUserWithDetails(Long userId) {
        //1. Get the user
        User user = findById(userId);
        //2. Convert the user to a userDto
        UserDto userDto = entityConverter.mapEntityToDto(user, UserDto.class);
        //3. Get user appointments (users(patient and a vet))
        setUserAppointment(userDto);
        setUserPhoto(userDto, user);
        
        return null;
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
}