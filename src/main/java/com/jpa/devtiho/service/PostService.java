package com.jpa.devtiho.service;

import com.jpa.devtiho.model.Posts;
import com.jpa.devtiho.repository.PostRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepo postRepo;

    public PostService(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    // Fetch all posts
    public List<Posts> getAllPosts() {
        return postRepo.findAll();
    }

    // Fetch a post by ID
    public Posts getPostById(Long id) {
        Optional<Posts> post = postRepo.findById(id);
        return post.orElse(new Posts());
    }

    // Create a new post
    public Posts createPost(Posts posts) {
        return postRepo.save(posts);
    }

    // Update an existing post
    public Posts updatePost(Long id, Posts updatedPost) {
        Optional<Posts> existingPost = postRepo.findById(id);
        if (existingPost.isPresent()) {
            Posts posts = existingPost.get();
            posts.setTitle(updatedPost.getTitle());
            posts.setContent(updatedPost.getContent());
            return postRepo.save(posts);
        }
        return null;
    }

    // Delete a post by ID
    public void deletePost(Long id) {
        postRepo.deleteById(id);
    }
}
