package com.jpa.devtiho.repository;

import com.jpa.devtiho.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepo extends JpaRepository<Posts, Long> {

    }
