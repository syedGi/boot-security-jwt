package com.springsecurityjwt.service;

import com.springsecurityjwt.dto.SignupRequest;
import com.springsecurityjwt.dto.UserDTO;

public interface AuthService {

	UserDTO createUser(SignupRequest signupRequest);

}
