package com.enviro.envirobank.service.impl;

import com.enviro.envirobank.dto.ChangePasswordRequest;
import com.enviro.envirobank.dto.LoginRequest;
import com.enviro.envirobank.exception.ResourceNotFoundException;
import com.enviro.envirobank.mail.EmailSender;
import com.enviro.envirobank.model.BankUser;
import com.enviro.envirobank.repository.BankUserRepository;
import com.enviro.envirobank.dto.JwtAuthorizationResponse;
import com.enviro.envirobank.security.BankUserDetailsService;
import com.enviro.envirobank.service.BankUserService;
import com.enviro.envirobank.utils.TokenProvider;
import com.enviro.envirobank.service.AuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@AllArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {
    private AuthenticationManager authenticationManager;
    private final BankUserRepository bankUserRepository;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final EmailSender emailSender;
    private TokenProvider jwtTokenProvider;
    private final BankUserService bankUserService;
    @Override
    public JwtAuthorizationResponse login(LoginRequest request) {
        Authentication authentication =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                       request.getUsernameOrEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        BankUser bankUser = bankUserRepository.findByUserNameOrEmail(authentication.getName(), authentication.getName()).orElseThrow();
        JwtAuthorizationResponse response = new JwtAuthorizationResponse();
        response.setInitials(bankUser.getFirstName().substring(0,1)+bankUser.getLastName().charAt(0));
        response.setAccessToken(jwtTokenProvider.generateToken(authentication));
        response.setRole(authentication.getAuthorities().toString());
        response.setUsername(authentication.getName());
        response.setId(bankUserService.id(authentication.getName()));

        return response;
    }

    @Override
    public void changePassword(ChangePasswordRequest request, String token) {
        BankUser bankUser = bankUserRepository.findByEmail(
                tokenProvider.getUsername(token)).orElseThrow(
                () -> new ResourceNotFoundException("User", "email address", tokenProvider.getUsername(token)));
        bankUser.setPassword(passwordEncoder.encode(request.getNewPassword()));
        bankUserRepository.save(bankUser);
    }

    @Override
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {
        var user = (UserDetails)((UsernamePasswordAuthenticationToken)connectedUser).getPrincipal();
        BankUser bankUser = bankUserRepository.findByUserNameOrEmail(user.getUsername(), user.getUsername())
                .orElseThrow();

        if(!passwordEncoder.matches(request.getOldPassword(),user.getPassword())){
            throw new RuntimeException("Incorrect password supplied");
        }
        if(!request.getNewPassword().equals(request.getConfirmationPassword())){
            throw new RuntimeException("New password and confirmation password do not match");
        }
        bankUser.setPassword(passwordEncoder.encode(request.getNewPassword()));
        bankUserRepository.save(bankUser);
    }

    @Override
    public void sendPasswordResetLink(String email) {
        BankUser bankUser = bankUserRepository.findByUserNameOrEmail(email,email).orElseThrow(()->
                new ResourceNotFoundException("User", "username or email", email));
        String token =tokenProvider.resetPasswordToken(bankUser.getEmail());
        System.out.println(token);
        String link = "http://localhost:4200/change-password?token="+token;
        emailSender.sendResetPasswordLink(bankUser.getFirstName(),link, bankUser.getEmail());
    }
}

