package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.auth.pojo.User;
import com.example.demo.auth.serv.UserServ;
import com.example.demo.pojo.Category;
import com.example.demo.pojo.Foto;
import com.example.demo.service.CategoryServ;

import jakarta.validation.Valid;

@Controller
public class CategoriesController {

	@Autowired
	private CategoryServ categoryServ;

	@Autowired
	private UserServ userServ;
	
	@GetMapping("/users/categories")
	public String catIndex(
			Model model
		) {
		
		List<Category> categories = categoryServ.findAll();
		model.addAttribute("categories", categories);
		
		return "categories-index";
	}
	
	@GetMapping("/users/categories/create")
	public String createCategory(
			Model model
		) {
		model.addAttribute("category", new Category());
		
		return "categories-create";
	}
	
	@PostMapping("/users/categories/create")
	public String storeCategory(
			Model model,
			@RequestParam String userName,
			@Valid @ModelAttribute Category category,
			BindingResult bindingResult
		) {
		if(bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach( (er) -> System.err.println("Errore: " + er.getDefaultMessage())  );
	
			model.addAttribute("category", category);
			model.addAttribute("errors", bindingResult);
			
			return "categories-create";
		}
		
		User catUser = userServ.findUserByUsername(userName);
		category.setUser(catUser);
		categoryServ.save(category);
		
		return "redirect:/users/categories";
	}
	
	@GetMapping("/users/categories/delete/{id}")
	public String deleteCategory(
			@PathVariable int id
		) {
		
		Optional<Category> optCat = categoryServ.findById(id);
		Category cat = optCat.get();
		List<Foto> fotoWithCat = cat.getFotos();
		
		for (Foto foto : fotoWithCat) {
			if(foto.getCategories().contains(cat)) {
				foto.getCategories().remove(cat);
			}
		}
		
		categoryServ.deleteCategory(cat);
		
		return "redirect:/users/categories";
	}
}
