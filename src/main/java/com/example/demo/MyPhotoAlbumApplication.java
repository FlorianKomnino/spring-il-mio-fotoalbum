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
import com.example.demo.pojo.Category;
import com.example.demo.pojo.Foto;
import com.example.demo.service.CategoryServ;
import com.example.demo.service.FotoServ;

@SpringBootApplication
public class MyPhotoAlbumApplication implements CommandLineRunner{

	
	@Autowired
	private RoleServ roleService;
	
	@Autowired
	private UserServ userService;
	
	@Autowired
	private FotoServ fotoServ;
	
	@Autowired
	private CategoryServ categoryServ;
	
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

		final String pws1 = new BCryptPasswordEncoder().encode("pws1");

		User baseU1 = new User("utente1", pws, baseUser);
		User baseU2 = new User("utente2", pws1, baseUser);
		User adminU = new User("admin", pws, adminUser);
		
		userService.save(adminU);
		userService.save(baseU1);
		userService.save(baseU2);
		
		Category c1 = new Category(baseU1, "Paesaggi");
		Category c2 = new Category(baseU1, "Ritratti");
		Category c3 = new Category(baseU1, "Natura morta");
		Category c4 = new Category(baseU1, "Astrologia");
		
		categoryServ.save(c1);
		categoryServ.save(c2);
		categoryServ.save(c3);
		categoryServ.save(c4);
		
		Foto f1 = new Foto(baseU1, "Prova 1", "foto di prova 1", "https://st4.depositphotos.com/14431644/22076/i/450/depositphotos_220767694-stock-photo-handwriting-text-writing-example-concept.jpg", true, c1);
		Foto f2 = new Foto(baseU1, "Prova 2", "foto di prova 2", "https://st4.depositphotos.com/14431644/22076/i/450/depositphotos_220767694-stock-photo-handwriting-text-writing-example-concept.jpg", true, c1);
		Foto f3 = new Foto(baseU1, "Prova 3", "foto di prova 3", "https://st4.depositphotos.com/14431644/22076/i/450/depositphotos_220767694-stock-photo-handwriting-text-writing-example-concept.jpg", false, c1);
		Foto f4 = new Foto(baseU1, "Prova 4", "foto di prova 4", "https://st4.depositphotos.com/14431644/22076/i/450/depositphotos_220767694-stock-photo-handwriting-text-writing-example-concept.jpg", true, c4);
		
		fotoServ.save(f1);
		fotoServ.save(f2);
		fotoServ.save(f3);
		fotoServ.save(f4);
		
		System.out.println("lots of things saved");
	}

}
