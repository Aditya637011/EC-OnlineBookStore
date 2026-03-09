package com.example.bookstore.repository;

import com.example.bookstore.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Coupon findByCodeAndActive(String code, boolean active);
}
