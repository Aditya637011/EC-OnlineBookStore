package com.example.bookstore.repository;

import com.example.bookstore.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepository extends JpaRepository<Banner, Long> {
}
