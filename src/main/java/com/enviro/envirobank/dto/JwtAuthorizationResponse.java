package com.enviro.envirobank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthorizationResponse {
    private String accessToken;
    private final String tokenType = "Bearer";
    private String role;
    private String id;
    private String username;
    private String initials;

}
