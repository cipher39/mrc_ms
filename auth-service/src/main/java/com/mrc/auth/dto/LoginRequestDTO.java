package com.mrc.auth.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class LoginRequestDTO {
	
	@Getter
	@Setter
	private String username;
	
	@Getter
	@Setter
	private String password;
}
