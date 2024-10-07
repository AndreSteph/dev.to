package com.jpa.devtiho.service;

import com.jpa.devtiho.model.Comment;
import com.jpa.devtiho.repository.CommentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepo commentRepo;

    private String encodeImage(byte[] imageData){
        return Base64.getEncoder().encodeToString(imageData);
    }

    @GetMapping("allComments")
    public List<Comment> getAllComments(){
        return commentRepo.findAll();
    }

    @GetMapping("getById/{comment}")
    public Optional<Comment> getCommentById(Long id){
        return commentRepo.findById(id);
    }

   public Comment addComment(Comment comment, MultipartFile imageFile) throws IOException{
        if( imageFile != null && !imageFile.isEmpty()){
            comment.setImageName(imageFile.getOriginalFilename());
            comment.setImageType(imageFile.getContentType());
            String base64Image = encodeImage(imageFile.getBytes());
        }
        return commentRepo.save(comment);
   }

    public ResponseEntity<String> updateComment (Comment comment1, Long commentId, MultipartFile imageFile) throws IOException{
        Optional<Comment> existingComment1 = commentRepo.findById(commentId);
        if(existingComment1.isPresent()) {
            Comment existingComment = existingComment1.get();
            existingComment.setContent(comment1.getContent());
            if (imageFile != null && !imageFile.isEmpty()) {
                existingComment.setImageName(imageFile.getOriginalFilename());
                existingComment.setImageType(imageFile.getContentType());
                String base64Image = encodeImage(imageFile.getBytes());
                comment1.setImageData(base64Image);
            }
            commentRepo.save(existingComment);
            return ResponseEntity.ok("Update successful");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }
    }

    public ResponseEntity<String> deleteComment(Long commentId){
        if(commentRepo.existsById(commentId)){
            commentRepo.deleteById(commentId);
            return ResponseEntity.ok("Deleted successfully");
        } else
            return ResponseEntity.status(404).body("User not found");
    }
}
