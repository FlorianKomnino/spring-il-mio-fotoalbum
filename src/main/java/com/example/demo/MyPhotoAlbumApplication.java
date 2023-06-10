package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.auth.pojo.Role;
import com.example.demo.auth.pojo.User;
import com.example.demo.auth.serv.RoleServ;
import com.example.demo.auth.serv.UserServ;

@SpringBootApplication
public class MyPhotoAlbumApplication implements CommandLineRunner{

	
	@Autowired
	private RoleServ roleService;
	
	@Autowired
	private UserServ userService;
	
	public static void main(String[] args) {
		SpringApplication.run(MyPhotoAlbumApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("Hello Universe!");
		Role baseUser = new Role("USER");
		Role adminUser = new Role("ADMIN");
		
		roleService.save(adminUser);
		roleService.save(baseUser);

		final String pws = new BCryptPasswordEncoder().encode("pws");
		
		User baseU = new User("utente", pws, baseUser);
		User adminU = new User("admin", pws, adminUser);
		
		userService.save(adminU);
		userService.save(baseU);
	}

}
