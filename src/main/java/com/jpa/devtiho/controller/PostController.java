package com.jpa.devtiho.controller;

import com.jpa.devtiho.model.Posts;
import com.jpa.devtiho.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // Get a list of all posts
    @GetMapping("/getAllPosts")
    public ResponseEntity<List<Posts>> getAllPosts() {
        List<Posts> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    // Get a specific post by ID
    @GetMapping("/getPostById/{id}")
    public ResponseEntity<Posts> getPostById(@PathVariable Long id) {
        Posts posts = postService.getPostById(id);
        return posts != null ? ResponseEntity.ok(posts) : ResponseEntity.notFound().build();
    }

    // Create a new post
    @PostMapping("/create")
    public ResponseEntity<Posts> createPost(@RequestBody Posts posts) {
        Posts newPost = postService.createPost(posts);
        return ResponseEntity.ok(newPost);
    }

    // Update an existing post
    @PutMapping("/update/{id}")
    public ResponseEntity<Posts> updatePost(@PathVariable Long id, @RequestBody Posts updatedPost) {
        Posts posts = postService.updatePost(id, updatedPost);
        return ResponseEntity.ok(posts);
    }

    // Delete a post
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok().build();
    }
}
