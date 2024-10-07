package com.jpa.devtiho.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String imageName;
    private String imageType;
    @Lob
    private String imageData;
    @ManyToOne
    private Users users;
    @ManyToOne
    private Posts posts;
}
