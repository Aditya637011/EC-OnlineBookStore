package com.example.bookstore.config;

import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Coupon;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.ReviewRepository;
import com.example.bookstore.repository.CartItemRepository;
import com.example.bookstore.repository.CouponRepository;
import com.example.bookstore.repository.BannerRepository;
import com.example.bookstore.entity.Banner;
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
                        CartItemRepository cartItemRepository, CouponRepository couponRepository,
                        BannerRepository bannerRepository) {
                return args -> {
                        if (bannerRepository.count() == 0) {
                                Banner b1 = new Banner();
                                b1.setTitle("Grand Book Sale");
                                b1.setDescription("Get up to 50% off on all bestsellers this summer!");
                                b1.setImageUrl("/banner1.png");
                                b1.setLink("#");
                                Banner b2 = new Banner();
                                b2.setTitle("Upcoming Mystery");
                                b2.setDescription("Pre-order 'The Mystery of Shadows' now for exclusive early access.");
                                b2.setImageUrl("/banner2.png");
                                b2.setLink("#");
                                Banner b3 = new Banner();
                                b3.setTitle("New Arrivals");
                                b3.setDescription("Explore the freshest reads curated just for you.");
                                b3.setImageUrl("/banner3.png");
                                b3.setLink("#");
                                bannerRepository.save(b1);
                                bannerRepository.save(b2);
                                bannerRepository.save(b3);
                                System.out.println("Banners Seeded!");
                        }
                        if (couponRepository.count() == 0) {
                                Coupon c1 = new Coupon();
                                c1.setCode("WELCOME20");
                                c1.setDiscountPercentage(20.0);
                                c1.setActive(true);
                                Coupon c2 = new Coupon();
                                c2.setCode("SAVE50");
                                c2.setDiscountPercentage(50.0);
                                c2.setActive(true);
                                Coupon c3 = new Coupon();
                                c3.setCode("ADITYA10");
                                c3.setDiscountPercentage(10.0);
                                c3.setActive(true);
                                couponRepository.save(c1);
                                couponRepository.save(c2);
                                couponRepository.save(c3);
                                System.out.println("Coupons Seeded!");
                        }

                        if (bookRepository.count() < 200) {
                                System.out.println("Clearing old data to insert new categories and books...");
                                cartItemRepository.deleteAll();
                                reviewRepository.deleteAll();
                                bookRepository.deleteAll();

                                List<Book> books = new ArrayList<>();
                                Random rand = new Random();

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
                                                { "Sherlock Holmes", "Arthur Conan Doyle", "Adventure", "1892-10-14",
                                                                "60 Million",
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
                                                { "1984", "George Orwell", "Science Fiction", "1949-06-08",
                                                                "30 Million",
                                                                "https://images.unsplash.com/photo-1509021436665-8f07cd6ec61e?auto=format&fit=crop&q=80&w=400" }
                                };

                                String[] cats = { "Horror", "Adventure", "Story", "Finance", "Science Fiction",
                                                "Self-Help" };
                                String dummyText = "Chapter 1\n\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua...";

                                for (String[] data : realBooks) {
                                        Book b = new Book(null, data[0], data[1], "A classic in " + data[2],
                                                        299 + rand.nextInt(500), dummyText, data[5], data[2], data[3],
                                                        data[4], 4.0 + rand.nextDouble());
                                        books.add(b);
                                }

                                for (int i = books.size(); i < 200; i++) {
                                        String c = cats[i % cats.length];
                                        books.add(new Book(null, c + " Saga Vol " + i, "Author " + i, "Desc",
                                                        199 + rand.nextInt(1000), dummyText,
                                                        "https://picsum.photos/seed/b" + i + "/400/600", c,
                                                        "2020-01-01", "1M", 3.0 + (rand.nextDouble() * 2)));
                                }
                                bookRepository.saveAll(books);
                                System.out.println("Library Seeded!");
                        }
                };
        }
}
