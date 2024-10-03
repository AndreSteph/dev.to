package com.jpa.devtiho.repository;

import com.jpa.devtiho.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository<Image, Long> {
}

