package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.pojo.Message;

public interface MessageRepo extends JpaRepository<Message, Integer> {

}
