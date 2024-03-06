package com.global.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Log4j2
//@CrossOrigin(value = {"http://localhost:4200", "https://google.com"})
public class UserController {

	private final UserService userService;


	@GetMapping("")
	public ResponseEntity<?> findAll() {

		return ResponseEntity.ok(userService.findAll());
	}

	@GetMapping("/{id}")
	@PreAuthorize("@userService.isOwner(authentication, #id)")
	public ResponseEntity<?> findById(@PathVariable Long id ) {

		return ResponseEntity.ok(userService.findById(id));
	}

}
