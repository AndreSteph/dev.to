package com.jpa.devtiho.service;

import com.jpa.devtiho.model.Image;
import com.jpa.devtiho.repository.ImageRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

@Service
public class ImageService {

    private final ImageRepo imageRepository;

    public ImageService(ImageRepo imageRepository) {
        this.imageRepository = imageRepository;
    }

    private String encodeImage(byte[] imageData){
        return Base64.getEncoder().encodeToString(imageData);
    }

    public ResponseEntity<?> uploadImage(Image image, MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()){
            image.setImageName(imageFile.getOriginalFilename());
            image.setImageType(imageFile.getContentType());

            //Convert image file to base 64
            String base64Image = encodeImage(imageFile.getBytes());
            image.setImageData(Arrays.toString(base64Image.getBytes()));
        }
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    public Image getImage(Long id) {
        return imageRepository.findById(id).orElse(null);
    }
}

