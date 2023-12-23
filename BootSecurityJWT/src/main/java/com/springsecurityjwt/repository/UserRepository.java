package com.springsecurityjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springsecurityjwt.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
