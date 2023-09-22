package com.enviro.envirobank.controller;

import com.enviro.envirobank.dto.LoginRequest;
import com.enviro.envirobank.repository.BankUserRepository;
import com.enviro.envirobank.security.JwtAuthorizationResponse;
import com.enviro.envirobank.security.JwtTokenProvider;
import com.enviro.envirobank.service.AuthorizationService;
import com.enviro.envirobank.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthorizationController {

    private final AuthorizationService authorizationService;
    @PostMapping("/login")
    public JwtAuthorizationResponse authenticateUser(@RequestBody LoginRequest loginRequest){
        String token = authorizationService.login(loginRequest);
        JwtAuthorizationResponse authorizationResponse = new JwtAuthorizationResponse();
        authorizationResponse.setAccessToken(token);
        return authorizationResponse;
    }
//      "http://localhost:8080/api/v1/auth/confirmToken?token="+savedToken;
    @GetMapping("confirmToken")
    public String confirmEmail(@RequestParam String name, @RequestParam String token, @RequestParam String email, Model model){

            return "jwtToken";
    }
}
