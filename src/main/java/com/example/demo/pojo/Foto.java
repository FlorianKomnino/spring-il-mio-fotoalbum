package com.example.demo.pojo;

import java.util.Arrays;
import java.util.List;

import com.example.demo.auth.pojo.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonSetter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Foto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	@NotNull
	private String title;
	private String description;
	private String url;
	private Boolean visible;
	private Boolean banned;

	@ManyToMany
	private List<Category> categories;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	@JsonBackReference
	private User user;

	public Foto() { }
	
	public Foto(User user, String title, String description, String url, Boolean visible) {
		
		setUser(user);
		setTitle(title);
		setDescription(description);
		setUrl(url);
		setVisible(visible);
		setBanned(false);
	}
	
	public Foto(User user, String title, String description, String url, Boolean visible, Category...categories) {

		setUser(user);
		setTitle(title);
		setDescription(description);
		setUrl(url);
		setVisible(visible);
		setBanned(false);
		
		setCategories(categories);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public List<Category> getCategories() {
		return categories;
	}
	@JsonSetter
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	public void setCategories(Category[] category) {
		
		setCategories(Arrays.asList(category));
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Boolean getBanned() {
		return banned;
	}
	
	public void setBanned(Boolean banned) {
		this.banned = banned;
	}

	@Override
	public String toString() {
		
		return "[" + getId() + "] " + getTitle() + " - " + (getVisible() == true ? "visibile" : "non visibile");
	}
}
