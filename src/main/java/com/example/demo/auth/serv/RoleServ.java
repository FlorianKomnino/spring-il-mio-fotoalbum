package com.example.demo.auth.serv;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.auth.pojo.Role;
import com.example.demo.auth.repo.RoleRepo;

@Service
public class RoleServ {

	@Autowired
	private RoleRepo roleRepo;
	
	public List<Role> findAll() {
		
		return roleRepo.findAll();
	}
	public Optional<Role> findById(int id) {
	
		return roleRepo.findById(id);
	}
	public Role save(Role role) {
		
		return roleRepo.save(role);
	}
}
