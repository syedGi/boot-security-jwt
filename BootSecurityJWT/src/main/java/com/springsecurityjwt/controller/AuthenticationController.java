package com.springsecurityjwt.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurityjwt.dto.AuthenticationRequest;
import com.springsecurityjwt.dto.AuthenticationResponse;
import com.springsecurityjwt.service.jwt.UserDetailsServiceImpl;
import com.springsecurityjwt.utils.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtils;
	
	@PostMapping("/authentication")
	public AuthenticationResponse createAuthenticationToken
	(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) 
				throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException{
		try {
			
		} catch (BadCredentialsException bce) {
			throw new BadCredentialsException("Incorrect Username or Password ...");
		} catch (DisabledException de) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not created. Register User First.");
			return null;
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
		final String jwt = jwtUtils.generateToken(userDetails.getUsername());
		return new AuthenticationResponse(jwt);
	}
}
