package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imageUrl;
    private String title;
    private String description;
    private String link;
}
