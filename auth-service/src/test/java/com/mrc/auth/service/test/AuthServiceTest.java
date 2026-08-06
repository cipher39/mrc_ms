package com.mrc.auth.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.mrc.auth.dto.UserResponseDTO;
import com.mrc.auth.entity.User;
import com.mrc.auth.repository.UserRepository;
import com.mrc.auth.service.AuthService;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private Authentication authentication;
	
	@Mock
	private SecurityContext securityContext;
	
	@InjectMocks
	private AuthService authService;
	
	User user = new User();
	
	@BeforeEach
	void init() {
		user.setUsername("rahul");
		user.setEmail("rahul123@gmail.com");
		
		SecurityContextHolder.setContext(securityContext);
		
		when(authentication.getName()).thenReturn("rahul");
		when(securityContext.getAuthentication()).thenReturn(authentication);		
	}
	
	@Test
	public void getCurrentUserTest() {
		when(userRepository.findByUsername("rahul")).thenReturn(Optional.of(user));
		UserResponseDTO userDto = authService.getCurrentUser();
		System.out.println("inside tests userDto: " + userDto);
		assertEquals("rahul123@gmail.com", userDto.getEmail());		
	}

}
