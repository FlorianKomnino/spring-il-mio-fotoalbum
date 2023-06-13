package com.example.demo.auth.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.auth.pojo.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	public Optional<User> findByUsername(String username);
}
