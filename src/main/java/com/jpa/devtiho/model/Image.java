package com.jpa.devtiho.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageName;
    private String imageType;

    public void setImageData(String base64Image) {

    }

    // Store image as Base64-encoded string
    @Column(columnDefinition = "TEXT")
    private byte[] imageData;

    @OneToOne
    private Posts posts;
    @OneToOne
    private Users users;


}

