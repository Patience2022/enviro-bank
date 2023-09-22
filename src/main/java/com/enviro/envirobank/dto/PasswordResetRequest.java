package com.enviro.envirobank.dto;

import lombok.Data;

@Data
public class PasswordResetRequest {
    private String name;
    private String email;
    private String token;
}
