package com.example.bookstore.controller;

import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Review;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/{bookId}")
    public List<Review> getReviewsByBookId(@PathVariable Long bookId) {
        return reviewRepository.findByBookId(bookId);
    }

    @PostMapping
    public Review addReview(@RequestBody Review review) {
        Review saved = reviewRepository.save(review);
        updateBookRating(review.getBookId());
        return saved;
    }

    private void updateBookRating(Long bookId) {
        List<Review> reviews = reviewRepository.findByBookId(bookId);
        if (!reviews.isEmpty()) {
            double avg = reviews.stream().mapToInt(Review::getRating).average().orElse(0.0);
            Book book = bookRepository.findById(bookId).orElse(null);
            if (book != null) {
                book.setRating(avg);
                bookRepository.save(book);
            }
        }
    }
}
