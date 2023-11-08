package com.enviro.envirobank.service;

import com.enviro.envirobank.dto.ChangePasswordRequest;
import com.enviro.envirobank.dto.LoginRequest;
import com.enviro.envirobank.dto.JwtAuthorizationResponse;

import java.security.Principal;

public interface AuthorizationService {

    JwtAuthorizationResponse login(LoginRequest request);
    void changePassword(ChangePasswordRequest request, String token);
    void changePassword(ChangePasswordRequest request, Principal connectedUser);

    void sendPasswordResetLink(String email);

}
