package com.example.demo.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomAuthController {

	@GetMapping("/")
	public String getGuestHome() {
		
		return "redirect:/users/fotos";
	}
	
	@GetMapping("/users")
	public String getUserHome() {
		
		return "userIndex";
	}
	
	@GetMapping("/admin")
	@ResponseBody
	public String getAdminHome() {
		
		return "admins - only admin";
	}
}
