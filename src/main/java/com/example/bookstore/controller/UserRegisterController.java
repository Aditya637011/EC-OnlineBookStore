package com.example.bookstore.controller;

import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstore.entity.UserRegister;
import com.example.bookstore.model.ResponseMessage;
import com.example.bookstore.service.UserRegisterService;
import com.example.bookstore.utility.Constants;

@RestController
public class UserRegisterController {

	@Autowired
	private UserRegisterService userRegisterService;

	@PostMapping("/userregisters")
	public ResponseEntity<ResponseMessage> createUserRegister(@RequestBody UserRegister userRegister) {

		String insertUserRegister = userRegisterService.insertUserRegister(userRegister);

		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
				"online bookstore save successfully", insertUserRegister));

	}

	@GetMapping("/userregisters")
	public ResponseEntity<ResponseMessage> getUserRegisters() {
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS,
				"Users fetched successfully", userRegisterService.getAllUsers()));
	}

}
