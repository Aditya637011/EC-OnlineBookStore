package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "coupons")
@Data
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    private double discountPercentage;
    private boolean active;
}
