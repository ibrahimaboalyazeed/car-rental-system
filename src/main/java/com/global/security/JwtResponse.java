package com.global.security;

import java.util.Set;

import com.global.entity.Role;

public class JwtResponse {

	private String token;
	private Long id;
	private String email;
	private String username;
	private Set<Role> roles;
	private String status;


	
	

	public JwtResponse(String token, Long id ,String email ,Set<Role> roles, String status) {
		super();
		this.token = token;
		this.id =id;
		this.email = email;
		this.roles = roles;
		this.status = status;
	}

	public JwtResponse() {

	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	

}