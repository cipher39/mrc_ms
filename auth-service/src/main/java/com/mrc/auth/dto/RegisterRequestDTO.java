package com.mrc.auth.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RegisterRequestDTO {
	private String username;
	private String password;
	private String fullName;
	private String email;
}
