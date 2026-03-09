package com.example.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookstore.entity.UserRegister;

@Repository
public interface UserRegisterRepository extends JpaRepository<UserRegister, Long> {
}
