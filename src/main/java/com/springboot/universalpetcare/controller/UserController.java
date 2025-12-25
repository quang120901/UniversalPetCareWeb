package com.springboot.universalpetcare.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.universalpetcare.dto.EntityConverter;
import com.springboot.universalpetcare.dto.UserDto;
import com.springboot.universalpetcare.exception.ResourceNotFoundException;
import com.springboot.universalpetcare.exception.UserAlreadyExistsException;
import com.springboot.universalpetcare.model.User;
import com.springboot.universalpetcare.request.RegistrationRequest;
import com.springboot.universalpetcare.request.UserUpdateRequest;
import com.springboot.universalpetcare.respone.ApiResponse;
import com.springboot.universalpetcare.service.user.UserService;
import com.springboot.universalpetcare.ultis.FeedBackMessage;
import com.springboot.universalpetcare.ultis.UrlMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(UrlMapping.USER)
@RestController
public class UserController {
    private final UserService userService;
    private final EntityConverter<User, UserDto> entityConverter;

    @PostMapping(UrlMapping.REGISTER_USER)
    public ResponseEntity<ApiResponse> register(@RequestBody RegistrationRequest request) {
        try {
            User user = userService.register(request);
            UserDto registeredUser = entityConverter.mapEntityToDto(user, UserDto.class);
            return ResponseEntity.ok(new ApiResponse(FeedBackMessage.SUCCESS, registeredUser));
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping(UrlMapping.UPDATE_USER)
    public ResponseEntity<ApiResponse> update(@PathVariable Long userId,@RequestBody UserUpdateRequest request) {
        try {
            User user = userService.update(userId, request);
            UserDto updateUser = entityConverter.mapEntityToDto(user, UserDto.class);
            return ResponseEntity.ok(new ApiResponse(FeedBackMessage.UPDATE_SUCCESS, updateUser));
        } catch (ResourceNotFoundException e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }


}
