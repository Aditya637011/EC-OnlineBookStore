package com.example.bookstore.controller;

import com.example.bookstore.entity.Coupon;
import com.example.bookstore.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coupons")
public class CouponController {

    @Autowired
    private CouponRepository couponRepository;

    @GetMapping("/validate/{code}")
    public Coupon validateCoupon(@PathVariable String code) {
        return couponRepository.findByCodeAndActive(code, true);
    }
}
