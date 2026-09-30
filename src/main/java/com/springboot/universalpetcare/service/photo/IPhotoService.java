package com.springboot.universalpetcare.service.photo;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.springboot.universalpetcare.model.Photo;

public interface IPhotoService {
    Photo savePhoto(MultipartFile file, Long userId);
    Optional<Photo> getPhotoById(Long id);
    void deletePhoto(Long id);
    Photo updatePhoto(Long id, byte[] imageData);
    byte[] getImageData(Long id);
}
