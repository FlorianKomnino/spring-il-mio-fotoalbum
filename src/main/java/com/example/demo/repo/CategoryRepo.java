package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.pojo.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
