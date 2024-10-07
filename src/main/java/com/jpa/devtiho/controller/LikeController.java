package com.jpa.devtiho.controller;

import com.jpa.devtiho.model.Likes;
import com.jpa.devtiho.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes/")
public class LikeController {

    private final LikeService likeService;

   // Endpoint to create a new like
    @PostMapping("likePost")
    public ResponseEntity<Likes> createLike(@RequestBody Likes likes) {
        Likes savedLike = likeService.saveLike(likes);
        return ResponseEntity.ok(savedLike);
    }

    // Endpoint to get all likes
    @GetMapping("getAllLikes")
    public ResponseEntity<List<Likes>> getAllLikes() {
        List<Likes> likes = likeService.getAllLikes();
        return ResponseEntity.ok(likes);
    }

    // Endpoint to get a like by ID
    @GetMapping("getLikeById/{id}")
    public ResponseEntity<Likes> getLikeById(@PathVariable Long id) {
        Optional<Likes> like = likeService.getLikeById(id);
        return like.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint to delete a like by ID
    @DeleteMapping("/deleteLike/{id}")
    public ResponseEntity<Void> deleteLike(@PathVariable Long id) {
        likeService.deleteLike(id);
        return ResponseEntity.noContent().build();
    }
}

