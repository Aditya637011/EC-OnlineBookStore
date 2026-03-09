package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cart_items")
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId; // For simplicity in linking to the UserRegister

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private int quantity;
}
