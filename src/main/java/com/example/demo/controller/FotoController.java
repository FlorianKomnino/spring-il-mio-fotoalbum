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
import com.example.demo.service.FotoServ;

import jakarta.validation.Valid;

@Controller
public class FotoController {
	
	@Autowired
	private FotoServ fotoServ;
	
	@Autowired
	private UserServ userServ;
	
	@Autowired
	private CategoryServ categoryServ;
	
	@GetMapping("/users/fotos")
	public String index(Model model) {
		
		List<Foto> foundFotos = fotoServ.findAll();
		model.addAttribute("fotos", foundFotos);
		return "index";
	}
	
	@GetMapping("/admin/fotos")
	public String adminIndex(Model model) {
		
		List<Foto> foundFotos = fotoServ.findAll();
		model.addAttribute("fotos", foundFotos);
		return "admin-index";
	}
	
	@GetMapping("/admin/fotos/ban/{id}")
	public String banFoto(@PathVariable int id) {
		
		Optional<Foto> optFoto = fotoServ.findById(id);
		Foto fotoToUpdateBan = optFoto.get();
		fotoToUpdateBan.setBanned(true);
		fotoServ.save(fotoToUpdateBan);
		
		return "redirect:/admin/fotos";
	}

	@GetMapping("/admin/fotos/clearBan/{id}")
	public String clearBanFoto(@PathVariable int id) {
		
		Optional<Foto> optFoto = fotoServ.findById(id);
		Foto fotoToUpdateBan = optFoto.get();
		fotoToUpdateBan.setBanned(false);
		fotoServ.save(fotoToUpdateBan);
		
		return "redirect:/admin/fotos";
	}
	
	@GetMapping("/users/fotos/search")
	public String findByName(Model model, @RequestParam(required = false) String userSearch) {
		
		if(userSearch == "" || userSearch == null) {
			List<Foto> foundFotos = fotoServ.findAll();
			model.addAttribute("fotos", foundFotos);
			return "index";
		} else {
			
			List<Foto> foundFotos = fotoServ.findByTitle(userSearch);
			model.addAttribute("fotos", foundFotos);
			return "index";
		}
	}
	
	@GetMapping("/users/fotos/{id}")
	public String findById(
			Model model, 
			@PathVariable("id") int id
			) {
		
		Optional<Foto> optFoto = fotoServ.findById(id);
		Foto foto = optFoto.get();
		List<Category> fotoCat = foto.getCategories();
		
		model.addAttribute("foto", foto);
		model.addAttribute("categories", fotoCat);
		return "single-foto";
	}
	
	@GetMapping("/users/fotos/create")
	public String createFoto(Model model) {
		
		List<Category> catList = categoryServ.findAll();
		model.addAttribute("categories", catList);
		model.addAttribute("foto", new Foto());
		return "foto-create";
	}
	
	@PostMapping("/users/fotos/create")
	public String storeFoto(
			Model model,
			@RequestParam String userName,
			@Valid @ModelAttribute Foto foto,
			BindingResult bindingResult
			) {
		
		if(bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach( (er) -> System.err.println("Errore: " + er.getDefaultMessage())  );
			
			model.addAttribute("foto", foto);
			model.addAttribute("errors", bindingResult);
			
			return "foto-create";
		}
		User fotoUser = userServ.findUserByUsername(userName);
		foto.setUser(fotoUser);
		
		fotoServ.save(foto);
		
		for (Category cat : foto.getCategories()) {
			cat.addFoto(foto);
			categoryServ.save(cat);
		}
		
		return "redirect:/";
	}

	//passes new Foto and categories List through Model
	@GetMapping("/users/fotos/update/{id}")
	public String updateFoto(
			Model model,
			@PathVariable("id") int id
		) {
		
		Optional<Foto> optFotoToUpdate = fotoServ.findById(id);
		Foto fotoToUpdate = optFotoToUpdate.get();
		List<Category> catList = categoryServ.findAll();
		
		model.addAttribute("categories", catList);
		model.addAttribute("fotoToUpdate", fotoToUpdate);
		
		return "foto-update";
	}
		
	//implements update CRUD with categories sync
	@PostMapping("/users/fotos/update/{id}")
	public String updateFoto(
			Model model,
			@PathVariable("id") int id,
			@RequestParam String userName,
			@Valid @ModelAttribute Foto foto,
			BindingResult bindingResult
		) {
		
		if(bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach( (er) -> System.err.println("Errore: " + er.getDefaultMessage())  );
			
			model.addAttribute("fotoToUpdate", foto);
			model.addAttribute("errors", bindingResult);
			
			return "foto-update";
		}
		User fotoUser = userServ.findUserByUsername(userName);
		foto.setUser(fotoUser);
		fotoServ.save(foto);
		
		for (Category cat : foto.getCategories()) {
			
			cat.addFoto(foto);
			categoryServ.save(cat);
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/users/fotos/delete/{id}")
	public String deleteFoto(
			@PathVariable int id
		) {
		
		Optional<Foto> optFoto = fotoServ.findById(id);
		Foto foto = optFoto.get();
		foto.getCategories().clear();
		fotoServ.deleteFoto(foto);
		
		return "redirect:/users/fotos";
	}
	
	
}
