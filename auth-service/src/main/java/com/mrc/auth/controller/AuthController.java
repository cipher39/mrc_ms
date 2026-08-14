package com.mrc.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mrc.auth.dto.LoginRequestDTO;
import com.mrc.auth.dto.LoginResponseDTO;
import com.mrc.auth.dto.RegisterRequestRecord;
import com.mrc.auth.dto.UserResponseDTO;
import com.mrc.auth.dto.UserResponseRecord;
import com.mrc.auth.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestRecord request){
		System.out.println("Inside Resgister: 1: " + request.toString());
		UserResponseRecord response = authService.register(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PostMapping(value = "/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginDto) {
		LoginResponseDTO login = authService.login(loginDto);
		return ResponseEntity.status(HttpStatus.OK).body(login);
	}
	
	
	@GetMapping(value = "/user/me")
	public ResponseEntity<UserResponseDTO> getCurrentUser(){
		UserResponseDTO user = authService.getCurrentUser();
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
//	GET  /api/users/me
}
