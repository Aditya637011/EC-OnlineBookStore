package com.example.bookstore.controller;

import com.example.bookstore.entity.CartItem;
import com.example.bookstore.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartItemRepository cartRepository;

    @GetMapping("/{userId}")
    public List<CartItem> getCart(@PathVariable Long userId) {
        return cartRepository.findByUserId(userId);
    }

    @PostMapping
    public CartItem addToCart(@RequestBody CartItem cartItem) {
        if (cartItem.getBook() == null || cartItem.getBook().getId() == null) {
            throw new IllegalArgumentException("Book ID is required");
        }

        CartItem existing = cartRepository.findByUserIdAndBookId(cartItem.getUserId(), cartItem.getBook().getId());
        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + cartItem.getQuantity());
            return cartRepository.save(existing);
        }
        return cartRepository.save(cartItem);
    }

    @DeleteMapping("/{id}")
    public void removeFromCart(@PathVariable Long id) {
        cartRepository.deleteById(id);
    }

    @DeleteMapping("/user/{userId}")
    public void clearCart(@PathVariable Long userId) {
        List<CartItem> items = cartRepository.findByUserId(userId);
        cartRepository.deleteAll(items);
    }
}
