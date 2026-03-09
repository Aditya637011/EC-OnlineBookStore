package com.example.bookstore.controller;

import com.example.bookstore.entity.WishlistItem;
import com.example.bookstore.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    @Autowired
    private WishlistRepository wishlistRepository;

    @GetMapping("/{userId}")
    public List<WishlistItem> getWishlist(@PathVariable Long userId) {
        return wishlistRepository.findByUserId(userId);
    }

    @PostMapping
    public WishlistItem addToWishlist(@RequestBody WishlistItem item) {
        WishlistItem existing = wishlistRepository.findByUserIdAndBookId(item.getUserId(), item.getBook().getId());
        if (existing != null)
            return existing;
        return wishlistRepository.save(item);
    }

    @DeleteMapping("/{id}")
    public void removeFromWishlist(@PathVariable Long id) {
        wishlistRepository.deleteById(id);
    }
}
