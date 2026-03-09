package com.example.bookstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookstore.entity.UserRegister;
import com.example.bookstore.repository.UserRegisterRepository;
import com.example.bookstore.service.UserRegisterService;

@Service
public class UserRegisterServiceImpl implements UserRegisterService {

    @Autowired
    private UserRegisterRepository userRegisterRepository;

    @Override
    public String insertUserRegister(UserRegister userRegister) {
        userRegisterRepository.save(userRegister);
        return "User registered successfully";
    }

    @Override
    public List<UserRegister> getAllUsers() {
        return userRegisterRepository.findAll();
    }
}
