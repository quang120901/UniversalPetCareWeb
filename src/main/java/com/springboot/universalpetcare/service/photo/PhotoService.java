package com.springboot.universalpetcare.service.photo;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.springboot.universalpetcare.model.Photo;

public class PhotoService implements IPhotoService{

    @Override
    public Photo savePhoto(MultipartFile file, Long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'savePhoto'");
    }

    @Override
    public Optional<Photo> getPhotoById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPhotoById'");
    }

    @Override
    public void deletePhoto(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePhoto'");
    }

    @Override
    public Photo updatePhoto(Long id, byte[] imageData) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePhoto'");
    }

    @Override
    public byte[] getImageData(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getImageData'");
    }
    
}
