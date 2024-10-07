package com.jpa.devtiho.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpa.devtiho.model.Comment;
import com.jpa.devtiho.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments/")
public class CommentController {

    private final CommentService commentService;

    @PostMapping(value = "addComment", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Object addComment(@RequestPart("comment") String commentJson, @RequestPart("imageFile") MultipartFile imageFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Comment comment = objectMapper.readValue(commentJson, Comment.class);
        return commentService.addComment(comment, imageFile);
    }

    @PutMapping("updateComment")
    public ResponseEntity<String> updateComment(@RequestPart("comment") String commentJson, @PathVariable Long id, @RequestPart("imageFile") MultipartFile imageFile) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        Comment comment = objectMapper.readValue(commentJson, Comment.class);
        return commentService.updateComment(comment,id, imageFile);
    }

    @DeleteMapping(path ="delete/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id){
        return commentService.deleteComment(id);
    }
}
