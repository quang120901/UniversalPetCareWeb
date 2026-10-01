package com.springboot.universalpetcare.controller;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.universalpetcare.exception.ResourceNotFoundException;
import com.springboot.universalpetcare.model.Photo;
import com.springboot.universalpetcare.respone.ApiResponse;
import com.springboot.universalpetcare.service.photo.IPhotoService;
import com.springboot.universalpetcare.ultis.FeedBackMessage;
import com.springboot.universalpetcare.ultis.UrlMapping;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import org.springframework.web.bind.annotation.GetMapping;



@RestController 
@RequestMapping(UrlMapping.PHOTOS)
@RequiredArgsConstructor 
public class PhotoController {
    private final IPhotoService photoService;

    @PostMapping(UrlMapping.UPLOAD_PHOTO)
    public ResponseEntity<ApiResponse> uploadPhoto(@RequestParam ("file") MultipartFile file, 
                                                    @RequestParam ("userId") Long userId) throws SQLException, IOException {
        try {
            Photo photo = photoService.savePhoto(file, userId);
            return ResponseEntity.ok(new ApiResponse(FeedBackMessage.CREATE_SUCCESS, null));
        } catch (IOException | SQLException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(FeedBackMessage.SERVER_ERROR, null));
        }
    }

    @PutMapping(UrlMapping.UPDATE_PHOTO)
    public ResponseEntity<ApiResponse> updatePhoto(@PathVariable Long photoId, byte[] photoBytes) {
        try {
            Photo photo = photoService.updatePhoto(photoId, photoBytes);
            return ResponseEntity.ok(new ApiResponse(FeedBackMessage.CREATE_SUCCESS, null));
        } catch (ResourceNotFoundException | SQLException | IOException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(FeedBackMessage.RESOURCE_NOT_FOUND, null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(FeedBackMessage.SERVER_ERROR, null));
        }
    }

    @DeleteMapping (UrlMapping.DELETE_PHOTO)
    public ResponseEntity<ApiResponse> deletePhoto(@PathVariable Long photoId) {
        try {
            photoService.deletePhoto(photoId);
            return ResponseEntity.ok(new ApiResponse(FeedBackMessage.DELETE_SUCCESS, null));
        } catch (ResourceNotFoundException | SQLException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(FeedBackMessage.RESOURCE_NOT_FOUND, null));
        }
    }

    @GetMapping(UrlMapping.GET_PHOTO_BY_ID)
    public ResponseEntity<ApiResponse> getPhotoById(@PathVariable Long photoId) {
        try {
            Photo thePhoto = photoService.getPhotoById(photoId);
            return ResponseEntity.ok(new ApiResponse(FeedBackMessage.RESOURCE_FOUND, thePhoto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(FeedBackMessage.RESOURCE_NOT_FOUND, null));
        }
    }

}
