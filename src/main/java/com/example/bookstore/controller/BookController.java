package com.example.bookstore.controller;

import com.example.bookstore.entity.Book;
import com.example.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List<Book> getAllBooks(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) String sortBy) {

        Sort sort = Sort.unsorted();
        if ("priceAsc".equals(sortBy))
            sort = Sort.by(Sort.Direction.ASC, "price");
        else if ("priceDesc".equals(sortBy))
            sort = Sort.by(Sort.Direction.DESC, "price");
        else if ("ratingDesc".equals(sortBy))
            sort = Sort.by(Sort.Direction.DESC, "rating");

        List<Book> books = bookRepository.findAll(sort);

        return books.stream()
                .filter(b -> search == null || b.getTitle().toLowerCase().contains(search.toLowerCase())
                        || b.getAuthor().toLowerCase().contains(search.toLowerCase()))
                .filter(b -> category == null || "All".equals(category) || category.equals(b.getCategory()))
                .filter(b -> minPrice == null || b.getPrice() >= minPrice)
                .filter(b -> maxPrice == null || b.getPrice() <= maxPrice)
                .filter(b -> minRating == null || b.getRating() >= minRating)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @PostMapping("/admin")
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("/admin/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setPrice(bookDetails.getPrice());
            book.setCategory(bookDetails.getCategory());
            book.setCoverImageUrl(bookDetails.getCoverImageUrl());
            book.setDescription(bookDetails.getDescription());
            book.setPublishedDate(bookDetails.getPublishedDate());
            book.setWorldwideSales(bookDetails.getWorldwideSales());
            return bookRepository.save(book);
        }
        return null;
    }

    @DeleteMapping("/admin/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }
}
