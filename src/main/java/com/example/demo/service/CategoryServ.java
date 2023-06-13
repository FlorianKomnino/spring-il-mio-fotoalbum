package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.Category;
import com.example.demo.repo.CategoryRepo;

@Service
public class CategoryServ {

	@Autowired
	private CategoryRepo categoryRepo;
	
	public List<Category> findAll() {
		return categoryRepo.findAll();
	}
	
	public Category save(Category category) {
		return categoryRepo.save(category);
	}
	
	public Optional<Category> findById(int id) {
		return categoryRepo.findById(id);
	}
	
	public void deleteCategory(Category category) {
		categoryRepo.delete(category);
	}
}
