package com.example.bookstore.controller;

import com.example.bookstore.entity.Banner;
import com.example.bookstore.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banners")
public class BannerController {

    @Autowired
    private BannerRepository bannerRepository;

    @GetMapping
    public List<Banner> getAllBanners() {
        return bannerRepository.findAll();
    }

    @PostMapping("/admin")
    public Banner addBanner(@RequestBody Banner banner) {
        return bannerRepository.save(banner);
    }

    @PutMapping("/admin/{id}")
    public Banner updateBanner(@PathVariable Long id, @RequestBody Banner bannerDetails) {
        Banner banner = bannerRepository.findById(id).orElseThrow();
        banner.setImageUrl(bannerDetails.getImageUrl());
        banner.setTitle(bannerDetails.getTitle());
        banner.setLink(bannerDetails.getLink());
        return bannerRepository.save(banner);
    }

    @DeleteMapping("/admin/{id}")
    public void deleteBanner(@PathVariable Long id) {
        bannerRepository.deleteById(id);
    }
}
