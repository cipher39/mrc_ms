package com.mrc.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mrc.auth.dto.RegisterRequestDTO;
import com.mrc.auth.dto.UserResponseDTO;
import com.mrc.auth.service.AuthService;

@RestController
@RequestMapping(value="/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<UserResponseDTO> register(@RequestBody RegisterRequestDTO request){
		System.out.println("Inside Resgister: 1: " + request.toString());
		UserResponseDTO response = authService.register(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
//	POST /api/auth/register
//	POST /api/auth/login
//	GET  /api/users/me
}
