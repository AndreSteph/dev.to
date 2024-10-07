package com.jpa.devtiho.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpa.devtiho.model.Users;
import com.jpa.devtiho.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/")
public class UserController {

    private final UserService userService;

    private byte[] decodeImage(String base64ImageData) {

        return Base64.getDecoder().decode(base64ImageData);
    }

//    @RequestMapping("sectest")
//    public String securityTest(@RequestBody Users user){
//        return service.verify(user);
//    }

    // Retrieve all users
    @GetMapping(path = "all")
    public List<Users> findAllUsers() {
        return userService.findAllUsers();
    }

    // Retrieve Base64-encoded image by user ID
    @GetMapping(path = "image/{id}")
    public ResponseEntity<byte[]> getImageById(@PathVariable Long id) {
        Optional<Users> userOptional = userService.getUserById(id);
        if (userOptional.isPresent()) {
            Users users = userOptional.get();

            // Get the image data as a byte array
            byte[] imageData = decodeImage(Arrays.toString(users.getImageData())); // This should already be a byte array

            if (imageData != null && imageData.length > 0) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG) // Change this based on your image type
                        .body(imageData); // Return the byte array as the response body
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null); // No image data available
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // User not found
        }
    }



    // Add a new user with image
    @PostMapping(path= "addUser", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> addUser(@RequestPart("users") String userJson, @RequestPart("imageFile") MultipartFile imageFile) {
        try {
            // Convert the JSON string to a user object
            ObjectMapper objectMapper = new ObjectMapper();
            Users users = objectMapper.readValue(userJson, Users.class);

            ResponseEntity<?> user = userService.addUser(users, imageFile);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Update user information and image
    @PutMapping(path="update/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> updateUser(@RequestPart("user") String userJson, @PathVariable Long id,
                                             @RequestPart("imageFile") MultipartFile imageFile) {
        try {
            // Convert the JSON string to a user object
            ObjectMapper objectMapper = new ObjectMapper();
            Users users = objectMapper.readValue(userJson, Users.class);

            return userService.updateUser(users, imageFile, id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    // Delete user by ID
    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}


