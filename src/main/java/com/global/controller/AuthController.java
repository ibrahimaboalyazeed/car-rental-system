package com.global.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.global.entity.AppUser;
import com.global.error.CustomException;
import com.global.error.CustomResponse;
import com.global.security.AppUserDetail;
import com.global.security.JwtResponse;
import com.global.security.JwtTokenUtils;
import com.global.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@RestController
@Log4j2
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class AuthController {


	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtils tokenUtiles;
	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@PostMapping("/login")
	public Object login(@RequestBody Map<String, Object> body) {

		String email = (String) body.get("email");
		String password = (String) body.get("password");

		log.info("Authentication");
		Authentication authentication = null;

		try {

			authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		}

		catch (DisabledException dis) {
			throw new CustomException("USER_DISABLED");
		} catch (BadCredentialsException e) {
			throw new CustomException("Wrong email or password");
		}

		log.info("authentication >> " + authentication.isAuthenticated());
		if (authentication.isAuthenticated()) {
			log.info("authentication >> " + authentication.isAuthenticated());
			SecurityContextHolder.getContext().setAuthentication(authentication);
			//UserDetails userDetails = userService.loadUserByUsername(email);
			AppUser user = userService.findByEmail(email);
			String token = tokenUtiles.generateToken(email,false);

			return new CustomResponse(
					new JwtResponse(token, email, user.getRoles(), "Succeefully logged"));
		}

		return new CustomResponse("INVALID", HttpStatus.BAD_REQUEST.value());
	}

	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@PostMapping("/signup")
	public Object signup(@RequestBody AppUser user) {

		AppUser userNew = userService.save(user);

		// Authenticate the user
		log.info("Authentication");
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		log.info("authentication >> " + authentication.isAuthenticated());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Generate JWT token
		String token = tokenUtiles.generateToken(user.getEmail(), true);

		return new CustomResponse(
				new JwtResponse(token, user.getEmail(), userNew.getRoles(), "Successfully signed up"));
	}

}
