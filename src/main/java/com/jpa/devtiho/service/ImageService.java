package com.jpa.devtiho.service;

import com.jpa.devtiho.model.Image;
import com.jpa.devtiho.repository.ImageRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ImageService {

    private final ImageRepo imageRepository;

    public ImageService(ImageRepo imageRepository) {
        this.imageRepository = imageRepository;
    }

    public ResponseEntity<?> uploadImage(Image image, MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()){
            image.setImageName(imageFile.getOriginalFilename());
            image.setImageType(imageFile.getContentType());

            //Convert image file to base 64
            image.setImageData();
        }
        Map<String, String> response = new HashMap<>();
        response.put("message", "Image Uploaded");
        return ResponseEntity.ok(response);
    }

    public Image getImage(Long id) {
        return imageRepository.findById(id).orElse(null);
    }
}

