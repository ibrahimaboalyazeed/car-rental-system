package com.global.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;


import org.springframework.beans.factory.annotation.Value;


import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.Setter;

@Entity()
@Table(name = "users")
@Setter
@Getter
@AllArgsConstructor
public class AppUser {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	
	private String email;
	
	private String password ;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles" ,
	    joinColumns = @JoinColumn(name = "user_id"),
	    inverseJoinColumns = @JoinColumn(name = "role_id"))
	@OrderColumn(name = "id")
	private Set<Role> roles = new HashSet<>();
	
	@Value("1")
	private boolean isEnabled ;
	@Value("1")
	private boolean isCredentialsNonExpired;
	@Value("1")
	private boolean isAccountNonLocked;
	@Value("1")
	private boolean isAccountNonExpired;
	
	
	
	public AppUser(Long id) {
		super();
		this.id = id;
	}



	public AppUser() {
		super();
		this.isEnabled = true;
		this.isCredentialsNonExpired = true;
		this.isAccountNonLocked = true;
		this.isAccountNonExpired = true;
	}

}
