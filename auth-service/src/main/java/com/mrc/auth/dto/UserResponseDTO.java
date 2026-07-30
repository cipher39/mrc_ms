package com.mrc.auth.dto;

import java.util.Set;

import com.mrc.auth.entity.Role;

import lombok.Data;
import lombok.ToString;

@Data
public class UserResponseDTO {	
	private Long id;
	private String username;
	private String fullName;
	private String email;
	private Set<String> roles;
}
