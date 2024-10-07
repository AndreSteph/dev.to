package com.jpa.devtiho.service;

import com.jpa.devtiho.model.Likes;
import com.jpa.devtiho.repository.LikeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private LikeRepo likeRepo;

    // Create a new like
    public Likes saveLike(Likes likes) {
        return (Likes) likeRepo.save(likes);
    }

    // Get all likes
    public List<Likes> getAllLikes() {
        return likeRepo.findAll();
    }

    // Get a like by id
    public Optional<Likes> getLikeById(Long id) {
        return likeRepo.findById(id);
    }

    // Delete a like by id
    public void deleteLike(Long id) {
        likeRepo.deleteById(id);
    }
}
