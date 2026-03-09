package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "wishlist_items")
@Data
public class WishlistItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @ManyToOne
    private Book book;
}
