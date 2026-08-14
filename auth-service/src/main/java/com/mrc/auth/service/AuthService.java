package com.mrc.auth.service;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mrc.auth.dto.LoginRequestDTO;
import com.mrc.auth.dto.LoginResponseDTO;
import com.mrc.auth.dto.RegisterRequestDTO;
import com.mrc.auth.dto.UserResponseDTO;
import com.mrc.auth.dto.UserResponseRecord;
import com.mrc.auth.entity.Role;
import com.mrc.auth.entity.User;
import com.mrc.auth.exception.InvalidCredentialsException;
import com.mrc.auth.exception.ResourceNotFoundException;
import com.mrc.auth.repository.RoleRepository;
import com.mrc.auth.repository.UserRepository;

@Service
public class AuthService { 
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	/*
	 * register()
		login()
		getCurrentUser()
	 * 
	 * */
	
	public UserResponseDTO getCurrentUser() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();

		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("User " + username + "not found"));
		
		UserResponseDTO userDto = new UserResponseDTO();
		userDto.setId(user.getId());
		userDto.setUsername(username);
		userDto.setEmail(user.getEmail());
		userDto.setFullName(user.getFullName());
		userDto.setRoles(user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toSet()));
		
		return userDto;
	}
	
	public LoginResponseDTO login(LoginRequestDTO loginDto) {
		
		String username = loginDto.getUsername();
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException
						("User not found: " + username));
		
		boolean isValid = passwordEncoder.matches(loginDto.getPassword(), user.getPassword());
		
		LoginResponseDTO loginResponse = new LoginResponseDTO();
		if(isValid) {
			String token = jwtService.generateToken(user);
			
			Date expiry = jwtService.extractExpiration(token);
			long expiresIn = (expiry.getTime() - System.currentTimeMillis()) / 1000;
			
			loginResponse.setAccessToken(token);
			loginResponse.setExpiresIn(expiresIn);
			loginResponse.setUsername(jwtService.extractUsername(token));
			loginResponse.setRoles(user.getRoles()
                    .stream()
                    .map(Role::getRoleName)
                    .toList());
		}else throw new InvalidCredentialsException("Username or Password is Incorrect");
		return loginResponse;
	}
	
	public UserResponseRecord register(RegisterRequestDTO request) {
		if(userRepository.existsByUsername(request.getUsername())) {
			System.out.println("Username already Exists");
			return null;
		}
		if(userRepository.existsByEmail(request.getEmail())) {
			System.out.println("Email alread Exists");
			return null;
		}
		
		Role defaultRole = roleRepository.findByRoleName("BROKER");
		String encryptedPassword = passwordEncoder.encode(request.getPassword());
		
		User user = new User();
		user.setUsername(request.getUsername());
		user.setEmail(request.getEmail());
		user.setFullName(request.getFullName());
		user.setPassword(encryptedPassword);
		user.getRoles().add(defaultRole);
		user.setActive(true);
		
		user = userRepository.save(user);
		
		UserResponseRecord userDto = new UserResponseRecord(user.getId(), user.getUsername(), user.getFullName(), user.getEmail(),
		user.getRoles().stream().map(x -> x.getRoleName()).collect(Collectors.toSet()));
		return userDto;
	}
}
