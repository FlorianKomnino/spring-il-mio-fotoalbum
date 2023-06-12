package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.pojo.Foto;

public interface FotoRepo extends JpaRepository<Foto, Integer>{

	public List<Foto> findByTitleContaining(String title);
}
