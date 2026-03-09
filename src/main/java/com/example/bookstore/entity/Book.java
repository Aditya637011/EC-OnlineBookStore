package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;

    @Column(length = 2000)
    private String description;

    private double price;

    @Column(columnDefinition = "TEXT")
    private String content; // The actual book content for reading

    private String coverImageUrl;

    private String category;
    private String publishedDate;
    private String worldwideSales;

    private double rating;
}
