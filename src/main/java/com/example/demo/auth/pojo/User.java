package com.example.demo.auth.pojo;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.pojo.Category;
import com.example.demo.pojo.Foto;
import com.fasterxml.jackson.annotation.JsonSetter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class User implements UserDetails {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@NotBlank
	private String username;
	
	@NotNull
	@NotBlank
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> roles;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Foto> fotos;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Category> categories;
	
	public User() { }
	public User(String username, String password, Role... roles) {
		
		setUsername(username);
		setPassword(password);
		setRole(roles);
	}
	
	
	public Set<Role> getRoles() {
		
		return roles;
	}
	@JsonSetter
	public void setRoles(Set<Role> roles) {
		
		this.roles = roles;
	}
	public void setRole(Role[] roles) {
		
		setRoles(new HashSet<>(Arrays.asList(roles)));
	}

	public int getId() {
		
		return id;
	}
	public void setId(int id) {
		
		this.id = id;
	}
	public String getUsername() {
		
		return username;
	}
	public void setUsername(String username) {
		
		this.username = username;
	}
	public String getPassword() {
		
		return password;
	}
	public void setPassword(String password) {
		
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "[" + getId() + "]" + getUsername();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Set<GrantedAuthority> auths = new HashSet<>();
		
		for (Role r : getRoles()) 
			auths.add(new SimpleGrantedAuthority(r.getName()));
		
		return auths;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
