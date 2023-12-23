package com.springsecurityjwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurityjwt.dto.SignupRequest;
import com.springsecurityjwt.dto.UserDTO;
import com.springsecurityjwt.service.AuthService;

@RestController
public class SignupController {

	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
	ResponseEntity<?> createUser(@RequestBody SignupRequest signupRequest){
		UserDTO createdUser = authService.createUser(signupRequest);
		if(createdUser ==null)
			return new ResponseEntity<>("Usre is not created, try again later.", HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}
}
