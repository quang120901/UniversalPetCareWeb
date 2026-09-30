package com.springboot.universalpetcare.service.photo;

import java.io.IOException;
import java.sql.Blob;
import java.util.Optional;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.universalpetcare.exception.ResourceNotFoundException;
import com.springboot.universalpetcare.model.Photo;
import com.springboot.universalpetcare.model.User;
import com.springboot.universalpetcare.repository.PhotoRepository;
import com.springboot.universalpetcare.repository.UserRepository;
import com.springboot.universalpetcare.ultis.FeedBackMessage;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class PhotoService implements IPhotoService{
    private final PhotoRepository photoRepository;
    private final UserRepository userRepository;

    @Override
    public Photo savePhoto(MultipartFile file, Long userId) throws IOException, SQLException {
        Optional<User> theUser = userRepository.findById(userId);
        Photo photo = new Photo();
        if(file != null && !file.isEmpty()) {
            byte[] photoBytes = file.getBytes();
            Blob photoBlob = new SerialBlob(photoBytes);
            photo.setImage(photoBlob);
            photo.setFileType(file.getContentType());
        }
        Photo savedPhoto = photoRepository.save(photo);
        theUser.ifPresent(user -> {user.setPhoto(savedPhoto);});
        userRepository.save(theUser.get());
        return savedPhoto;
    }

    @Override
    public Optional<Photo> getPhotoById(Long id) {
        return photoRepository.findById(id);
    }

    @Override
    public void deletePhoto(Long id) {
        photoRepository.findById(id)
        .ifPresentOrElse(photoRepository::delete, ()->{
            throw new ResourceNotFoundException(FeedBackMessage.NOT_FOUND);
        });
    }

    @Override
    public Photo updatePhoto(Long id, byte[] imageData) {
        Optional<Photo> photo = photoRepository.findById(id);
        if(photo.isPresent()) {
            Photo thePhoto = photo.get();
            Blob photoBlob = new SerialBlob(imageData);
            thePhoto.setImage(photoBlob);
            return photoRepository.save(thePhoto);
        }
        throw new ResourceNotFoundException(FeedBackMessage.NOT_FOUND);
    }

    @Override
    public byte[] getImageData(Long id) {
        Optional<Photo> photo = getPhotoById(id);
        if(photo.isPresent()) {
            Blob photoBlob = photo.get().getImage();
            int blobLength = (int) photoBlob.length();
            return new byte[blobLength];
        }
        return new byte[0];
    }
    
}
