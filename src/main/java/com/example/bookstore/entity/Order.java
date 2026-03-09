package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private double totalAmount;
    private String orderStatus; // PROCESSING, SHIPPED, DELIVERED
    private String paymentMethod;
    private String deliveryMethod;

    @Column(length = 1000)
    private String shippingAddress;

    private LocalDateTime orderDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderItem> items;
}

@Entity
@Table(name = "order_items")
@Data
class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Book book;

    private int quantity;
    private double priceAtOrder;
}
