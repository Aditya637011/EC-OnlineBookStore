package com.example.bookstore.config;

import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Review;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.ReviewRepository;
import com.example.bookstore.repository.CartItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
public class DataLoader {

        @Bean
        CommandLineRunner initDatabase(BookRepository bookRepository, ReviewRepository reviewRepository,
                        CartItemRepository cartItemRepository) {
                return args -> {
                        // Check if we already have the new books, if not, let's clear and re-seed
                        boolean shouldSeed = false;
                        if (bookRepository.count() < 210) {
                                shouldSeed = true;
                        }

                        if (shouldSeed) {
                                System.out.println("Clearing old data to insert new categories and books...");
                                cartItemRepository.deleteAll();
                                reviewRepository.deleteAll();
                                bookRepository.deleteAll();

                                List<Book> books = new ArrayList<>();
                                Random rand = new Random();

                                // Specific requested real books and more
                                String[][] realBooks = {
                                                { "Rich Dad Poor Dad", "Robert T. Kiyosaki", "Finance", "1997-04-01",
                                                                "32 Million",
                                                                "https://images.unsplash.com/photo-1554224155-6726b3ff858f?auto=format&fit=crop&q=80&w=400" },
                                                { "The Psychology of Money", "Morgan Housel", "Finance", "2020-09-08",
                                                                "3 Million",
                                                                "https://images.unsplash.com/photo-1616514197671-15d99ce7a6f8?auto=format&fit=crop&q=80&w=400" },
                                                { "Atomic Habits", "James Clear", "Self-Help", "2018-10-16",
                                                                "15 Million",
                                                                "https://images.unsplash.com/photo-1589829085413-56de8ae18c73?auto=format&fit=crop&q=80&w=400" },
                                                { "The Shining", "Stephen King", "Horror", "1977-01-28", "10 Million",
                                                                "https://images.unsplash.com/photo-1505664194779-8d69ce112d76?auto=format&fit=crop&q=80&w=400" },
                                                { "It", "Stephen King", "Horror", "1986-09-15", "15 Million",
                                                                "https://images.unsplash.com/photo-1518709268805-4e9042af9f23?auto=format&fit=crop&q=80&w=400" },
                                                { "Dracula", "Bram Stoker", "Horror", "1897-05-26", "20 Million",
                                                                "https://images.unsplash.com/photo-1627953259461-125032890539?auto=format&fit=crop&q=80&w=400" },
                                                { "The Adventures of Sherlock Holmes", "Arthur Conan Doyle",
                                                                "Adventure", "1892-10-14", "60 Million",
                                                                "https://images.unsplash.com/photo-1601158935942-52255782d322?auto=format&fit=crop&q=80&w=400" },
                                                { "Treasure Island", "Robert Louis Stevenson", "Adventure",
                                                                "1883-05-23", "20 Million",
                                                                "https://images.unsplash.com/photo-1544413660-299165566b1d?auto=format&fit=crop&q=80&w=400" },
                                                { "The Alchemist", "Paulo Coelho", "Adventure", "1988-04-15",
                                                                "65 Million",
                                                                "https://images.unsplash.com/photo-1515255474630-6db70208b877?auto=format&fit=crop&q=80&w=400" },
                                                { "A Tale of Two Cities", "Charles Dickens", "Story", "1859-11-26",
                                                                "200 Million",
                                                                "https://images.unsplash.com/photo-1463320726281-696a485928c7?auto=format&fit=crop&q=80&w=400" },
                                                { "The Kite Runner", "Khaled Hosseini", "Story", "2003-05-29",
                                                                "31 Million",
                                                                "https://images.unsplash.com/photo-1532012197267-da84d127e765?auto=format&fit=crop&q=80&w=400" },
                                                { "Think and Grow Rich", "Napoleon Hill", "Finance", "1937-03-01",
                                                                "70 Million",
                                                                "https://images.unsplash.com/photo-1526304640581-d334cdbbf45e?auto=format&fit=crop&q=80&w=400" },
                                                { "To Kill a Mockingbird", "Harper Lee", "Story", "1960-07-11",
                                                                "40 Million",
                                                                "https://images.unsplash.com/photo-1544947950-fa07a98d237f?auto=format&fit=crop&q=80&w=400" },
                                                { "1984", "George Orwell", "Science Fiction", "1949-06-08",
                                                                "30 Million",
                                                                "https://images.unsplash.com/photo-1509021436665-8f07cd6ec61e?auto=format&fit=crop&q=80&w=400" },
                                                { "Harry Potter and the Sorcerer's Stone", "J.K. Rowling", "Adventure",
                                                                "1997-06-26", "120 Million",
                                                                "https://images.unsplash.com/photo-1618666012174-83b441c0bc76?auto=format&fit=crop&q=80&w=400" }
                                };

                                String[] explicitCategories = { "Horror", "Adventure", "Story", "Finance",
                                                "Science Fiction", "Self-Help" };

                                String dummyContent = "Chapter 1\n\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\n\nChapter 2\n\nSed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt.\n\n";
                                StringBuilder fullContent = new StringBuilder();
                                for (int i = 0; i < 3; i++)
                                        fullContent.append(dummyContent);

                                // Add real books
                                for (String[] bookData : realBooks) {
                                        Book book = new Book();
                                        book.setTitle(bookData[0]);
                                        book.setAuthor(bookData[1]);
                                        book.setCategory(bookData[2]);
                                        book.setPublishedDate(bookData[3]);
                                        book.setWorldwideSales(bookData[4]);
                                        book.setCoverImageUrl(bookData[5]);

                                        double priceInRupees = 299 + rand.nextInt(700); // 299 to 999
                                        book.setPrice(priceInRupees);
                                        book.setDescription("A masterpiece by " + bookData[1]
                                                        + ". This book explores deep themes within the " + bookData[2]
                                                        + " genre.");
                                        book.setContent(fullContent.toString());
                                        book.setRating(4.0 + (rand.nextDouble())); // 4.0 to 5.0

                                        books.add(book);
                                }

                                // Generate remaining books to reach 200 using the target categories
                                for (int i = books.size() + 1; i <= 200; i++) {
                                        String[] baseBook = realBooks[i % realBooks.length];
                                        Book book = new Book();
                                        book.setTitle("The " + explicitCategories[i % explicitCategories.length]
                                                        + " Chronicle Vol " + i);
                                        book.setAuthor("Author " + (100 + i));
                                        book.setCategory(explicitCategories[i % explicitCategories.length]);
                                        book.setPublishedDate("19" + (50 + rand.nextInt(50)) + "-0"
                                                        + (1 + rand.nextInt(9)) + "-1" + rand.nextInt(9));
                                        book.setWorldwideSales((1 + rand.nextInt(20)) + " Million");
                                        // Using picsum photos as dynamic but stable covers for the remaining portion
                                        book.setCoverImageUrl("https://picsum.photos/seed/genrebook" + i + "/400/600");

                                        double priceInRupees = 199 + rand.nextInt(1500);
                                        book.setPrice(priceInRupees);
                                        book.setDescription("A fantastic addition to the " + book.getCategory()
                                                        + " genre. Readers have loved volume " + i + ".");
                                        book.setContent(fullContent.toString());
                                        book.setRating(3.5 + (rand.nextDouble() * 1.5));

                                        books.add(book);
                                }

                                bookRepository.saveAll(books);

                                // Add dummy reviews for some books
                                List<Review> reviews = new ArrayList<>();
                                for (Book b : books) {
                                        if (rand.nextBoolean()) {
                                                Review r1 = new Review();
                                                r1.setBookId(b.getId());
                                                r1.setUsername("BookLover" + rand.nextInt(99));
                                                r1.setRating((int) Math.round(b.getRating()));
                                                r1.setComment("Absolutely amazing read! Perfectly captures the essence of "
                                                                + b.getCategory() + ".");
                                                reviews.add(r1);
                                        }
                                }
                                reviewRepository.saveAll(reviews);

                                System.out.println(
                                                "200 Books Loaded Successfully with Horror, Adventure, Story, Finance & more!");
                        }
                };
        }
}
