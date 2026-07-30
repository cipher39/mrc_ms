package com.mrc.auth.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {

    private String accessToken;

    private String tokenType = "Bearer";

    private Long expiresIn;

    private String username;

    private List<String> roles;
}