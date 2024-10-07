package com.jpa.devtiho.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpa.devtiho.model.Image;
import com.jpa.devtiho.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }



    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> uploadImage(@RequestPart("name") String imageJson,
                                         @RequestPart("imageFile") MultipartFile imageFile) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Image image = objectMapper.readValue(imageJson, Image.class);

            // Assuming ImageService.uploadImage is not static and is injected
            ResponseEntity<?> images = imageService.uploadImage(image, imageFile);

            // Return the result directly, no need to wrap it in another ResponseEntity
            return images;
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/getImage/{id}")
    public ResponseEntity<Image> getImage(@PathVariable Long id) {
        Image image = imageService.getImage(id);
        if (image != null) {
            return ResponseEntity.ok(image);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

