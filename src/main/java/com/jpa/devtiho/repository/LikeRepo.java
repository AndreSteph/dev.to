package com.jpa.devtiho.repository;

import com.jpa.devtiho.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepo extends JpaRepository<Likes, Long> {
}
