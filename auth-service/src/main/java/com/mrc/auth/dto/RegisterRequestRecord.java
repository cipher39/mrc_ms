package com.mrc.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestRecord(
		@NotBlank
		@Size(min = 3, max = 50, message = "Provide Proper Username")
		String username,
		
		@NotBlank
		String password,
		
		@NotBlank
		String fullName,
		
		@NotBlank
		@Email
		String email) {

}
