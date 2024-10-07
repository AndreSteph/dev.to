package com.jpa.devtiho.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String author;
    private int likedPost;
    private String mediaUrl;
    @ManyToOne
    private Users users;
    @OneToOne
    private Image image;

    // Fix the mappedBy property to point to 'posts'
    @OneToMany(mappedBy = "posts")
    private List<Comment> comment;

    @OneToMany
    private List<Likes> likes;

}
