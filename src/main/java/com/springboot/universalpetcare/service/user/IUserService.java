package com.springboot.universalpetcare.service.user;

import java.sql.SQLException;
import java.util.List;

import com.springboot.universalpetcare.dto.UserDto;
import com.springboot.universalpetcare.model.User;
import com.springboot.universalpetcare.request.RegistrationRequest;
import com.springboot.universalpetcare.request.UserUpdateRequest;

public interface IUserService {
    User register(RegistrationRequest request);
    User update(Long userId, UserUpdateRequest request);
    User findById(Long userId);
    void delete(Long userId);
    UserDto getUserWithDetails(Long userId) throws SQLException;
    List<UserDto> getAllUsers();
}
