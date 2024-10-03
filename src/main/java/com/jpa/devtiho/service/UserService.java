package com.jpa.devtiho.service;

import com.jpa.devtiho.model.Users;
import com.jpa.devtiho.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    // Utility to convert byte[] to Base64 string
    private String encodeImage(byte[] imageData) {
        return Base64.getEncoder().encodeToString(imageData);
    }

    // Utility to convert Base64 string back to byte[]
    private byte[] decodeImage(String base64ImageData) {
        return Base64.getDecoder().decode(base64ImageData);
    }

    // Retrieve all developers
    public List<Users> findAllUsers() {
        return userRepo.findAll();
    }

    // Delete a developer by ID
    public ResponseEntity<String> deleteUser(Long id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return ResponseEntity.ok("Deleted successfully");
        } else
            return ResponseEntity.status(404).body("User not found");
    }

    // Update a developer's details and image
    public ResponseEntity<String> updateUser(Users users, MultipartFile imageFile, Long id) throws IOException {
        Optional<Users> existingUser1 = userRepo.findById(id);
        if (existingUser1.isPresent()){
            Users existingUser = existingUser1.get();
            existingUser.setUsername(users.getUsername());
            existingUser.setBio(users.getBio());
            existingUser.setEmail(users.getEmail());
            if (imageFile != null && !imageFile.isEmpty()) {
                existingUser.setImageName(imageFile.getOriginalFilename());
                existingUser.setImageType(imageFile.getContentType());
                // Convert image file to Base64 string
                String base64Image = encodeImage(imageFile.getBytes());
                existingUser.setImageData(base64Image.getBytes());
            }
            userRepo.save(existingUser);
            return ResponseEntity.ok("Update successful");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }
    }


    // Add a new developer with an image
    public ResponseEntity<?> addUser(Users users, MultipartFile imageFile) throws IOException {
        String username = users.getUsername();
        Optional<Users> usernameExists = userRepo.findByUsername(username);

        // Check if username already exists
        if (usernameExists.isPresent()) {
            return ResponseEntity.status(409).body("Username already exists");
        }

        // Store image information
        if (imageFile != null && !imageFile.isEmpty()) {
            users.setImageName(imageFile.getOriginalFilename());
            users.setImageType(imageFile.getContentType());

            // Convert image file to Base64 string
            String base64Image = encodeImage(imageFile.getBytes());
            users.setImageData(base64Image.getBytes());
        }

        // Save the new developer
        userRepo.save(users);
        return ResponseEntity.ok("Saved successfully");
    }

    // Get developer by ID
    public Optional<Users> getUserById(Long id) {
        return userRepo.findById(id);
    }


}

//    public Optional<Users> findByUsername(String username) {
//        return userRepo.findByUsername(username);
//    }
//
//
//
//    public Users createUser(Users users) {
//        return userRepo.save(users);
//    }
//
//    public void deleteUser(Long id) {
//        userRepo.deleteById(id);
//
//    }
//
//
//
//    public List<Users> getUsers(){
//        return userRepo.findAll();
//    }
//}