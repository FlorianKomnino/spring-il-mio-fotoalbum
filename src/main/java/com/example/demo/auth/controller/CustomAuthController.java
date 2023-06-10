package com.example.demo.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomAuthController {

	@GetMapping("/")
	public String getGuestHome() {
		
		return "auth-home";
	}
	
	@GetMapping("/users")
	public String getUserHome() {
		
		return "auth-users";
	}
	
	@GetMapping("/admin")
	@ResponseBody
	public String getAdminHome() {
		
		return "admins - only admin";
	}
}
