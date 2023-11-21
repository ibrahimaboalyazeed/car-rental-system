package com.global.service;

import java.util.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.global.entity.AppUser;
import com.global.entity.Role;
import com.global.error.CustomException;
import com.global.repository.UserRepo;
import com.global.security.AppUserDetail;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

	private final UserRepo userRepo;

	private  final RoleService roleService;
	
	private final PasswordEncoder passwordEncoder;
	
	
	public AppUser findByEmail(String email) {
		
		return userRepo.findByEmail(email).orElseThrow(() -> new CustomException("User is not found"));
	}
	
	
	public List<AppUser> findAll (){
		
		return userRepo.findAll();
	}

	public List<AppUser> saveAll (List<AppUser> appUsers){

		return userRepo.saveAll(appUsers);
	}
	
    public AppUser findById (Long id){
		
		return userRepo.findById(id).orElseThrow(()-> new CustomException("This id is not found"));
	}
    
    public AppUser save(AppUser entity) {

		if(!userRepo.findByEmail(entity.getEmail()).isEmpty())
		{
			throw new CustomException("this email already exists");
		}
		AppUser appUser =new AppUser();
		appUser.setEmail(entity.getEmail());
		appUser.setPassword(passwordEncoder.encode(entity.getPassword()));
		// Get the customer role entity by name
		Role userRole = roleService.findByName("ROLE_USER");
		// Make sure the role exists
		if (userRole == null) {
			throw new RuntimeException("ROLE_USER not found in database!");
		}
		// Add the role to the customer's set of roles
		Set<Role> roles = new HashSet<>();
		roles.add(userRole);
		appUser.setRoles(roles);
		return userRepo.save(appUser);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	  Optional<AppUser> appUser =	userRepo.findByEmail(username);
	  
	  if (!appUser.isPresent()) {
		  
		  throw new UsernameNotFoundException("This User Not found with selected user name :- " + username);
	  }
		
		return new AppUserDetail(appUser.get());
	}


	
//	private static List<GrantedAuthority> getAuthorities(AppUser user) {
//		
//		List<GrantedAuthority> authorities = new ArrayList<>();
//		
//		 if(!user.getRoles().isEmpty()) {
//		        	user.getRoles().forEach(role -> {
//		        		authorities.add(new SimpleGrantedAuthority(role.getName()));	
//		        	});
//		        }
//		     return authorities;
//		}
}
