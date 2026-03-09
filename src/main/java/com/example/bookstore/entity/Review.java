package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "reviews")
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookId;
    private String username;
    private int rating;

    @Column(length = 2000)
    private String comment;
}
