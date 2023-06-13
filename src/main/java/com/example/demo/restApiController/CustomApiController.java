package com.example.demo.restApiController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Foto;
import com.example.demo.pojo.Message;
import com.example.demo.service.FotoServ;
import com.example.demo.service.MessageServ;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class CustomApiController {
	
	@Autowired
	private FotoServ fotoServ;
	
	@Autowired
	private MessageServ messageServ;
	
	@GetMapping("/fotos")
	public ResponseEntity<List<Foto>> getVisibleFotos() {
		
		List<Foto> allFotos = fotoServ.findAll();
		List<Foto> onlyVisibleFotos = new ArrayList<>();
		for (Foto foto : allFotos) {
			if (foto.getVisible()) {
				onlyVisibleFotos.add(foto);
			}
		}
		return new ResponseEntity<>(onlyVisibleFotos, HttpStatus.OK);
	}
	
	@PostMapping("/message")
	public void messagereceiver(
			@RequestBody Message message
		) {
		
		messageServ.save(message);
	}
}
