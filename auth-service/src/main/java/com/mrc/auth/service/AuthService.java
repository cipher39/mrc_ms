package com.mrc.auth.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrc.auth.dto.RegisterRequestDTO;
import com.mrc.auth.dto.UserResponseDTO;
import com.mrc.auth.entity.Role;
import com.mrc.auth.entity.User;
import com.mrc.auth.repository.RoleRepository;
import com.mrc.auth.repository.UserRepository;

@Service
public class AuthService { 
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	/*
	 * register()
		login()
		getCurrentUser()
	 * 
	 * */
	public UserResponseDTO register(RegisterRequestDTO request) {
		if(userRepository.existsByUsername(request.getUsername())) {
			System.out.println("Username already Exists");
			return null;
		}
		if(userRepository.existsByEmail(request.getEmail())) {
			System.out.println("Email alread Exists");
			return null;
		}
		
		Role defaultRole = roleRepository.findByRoleName("BROKER");
		
		User user = new User();
		user.setUsername(request.getUsername());
		user.setEmail(request.getEmail());
		user.setFullName(request.getFullName());
		user.setPassword(request.getPassword());
		user.getRoles().add(defaultRole);
		user.setActive(true);
		
		user = userRepository.save(user);
		
		UserResponseDTO userDto = new UserResponseDTO();
		userDto.setId(user.getId());
		userDto.setUsername(user.getUsername());
		userDto.setFullName(user.getFullName());
		userDto.setEmail(user.getEmail());
		Set<String> roles = user.getRoles().stream().map(x -> x.getRoleName()).collect(Collectors.toSet());
		userDto.setRoles(roles);
		return userDto;
	}
}
