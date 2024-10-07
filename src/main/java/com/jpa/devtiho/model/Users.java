package com.jpa.devtiho.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    private Long id;
    private String username;
    private String password;
    private String email;
    private String bio;
    private String imageName;
    private String imageType;
    private byte[] imageData;

    @OneToOne
    private Image image;

    @OneToMany(mappedBy = "users")
    private List<Posts> posts;

    @OneToOne
    private Likes likes;

}

