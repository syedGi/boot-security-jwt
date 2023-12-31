package com.springsecurityjwt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

	/*
	 * public ResponseEntity<String> hello(){ return new
	 * ResponseEntity<String>("Hello from JWT Authentication", HttpStatus.OK); }
	 */
	
	@GetMapping("/hello")
	public String hello(){
		return "Hello from JWT Authentication";
	}
}
