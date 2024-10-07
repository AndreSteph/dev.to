package com.jpa.devtiho.repository;

import com.jpa.devtiho.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Long> {
}
