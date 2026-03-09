package com.example.bookstore.controller;

import com.example.bookstore.entity.Order;
import com.example.bookstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping
    public Order placeOrder(@RequestBody Order order) {
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus("PROCESSING");
        return orderRepository.save(order);
    }

    @GetMapping("/user/{userId}")
    public List<Order> getUserOrders(@PathVariable Long userId) {
        return orderRepository.findByUserIdOrderByOrderDateDesc(userId);
    }
}
