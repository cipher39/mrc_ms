package com.mrc.auth.dto;

import java.util.Set;

public record UserResponseRecord(
		Long id, 
		String username, 
		String fullName, 
		String email, 
		Set<String> roles) {
}
