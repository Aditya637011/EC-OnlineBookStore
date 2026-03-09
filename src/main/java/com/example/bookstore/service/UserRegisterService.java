package com.example.bookstore.service;

import java.util.List;

import com.example.bookstore.entity.UserRegister;

public interface UserRegisterService {
    String insertUserRegister(UserRegister userRegister);

    List<UserRegister> getAllUsers();
}
