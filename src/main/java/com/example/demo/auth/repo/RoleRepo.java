package com.example.demo.auth.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.auth.pojo.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
