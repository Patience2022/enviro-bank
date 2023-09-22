package com.enviro.envirobank.service.impl;

import com.enviro.envirobank.dto.LoginRequest;
import com.enviro.envirobank.security.JwtTokenProvider;
import com.enviro.envirobank.service.AuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {
    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;
    @Override
    public String login(LoginRequest request) {
        Authentication authentication =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                       request.getUsernameOrEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);
    }


}
