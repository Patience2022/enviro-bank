package com.enviro.envirobank.controller;

import com.enviro.envirobank.dto.LoginRequest;
import com.enviro.envirobank.dto.ChangePasswordRequest;
import com.enviro.envirobank.dto.ResetPasswordRequest;
import com.enviro.envirobank.dto.JwtAuthorizationResponse;
import com.enviro.envirobank.service.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthorizationController {

    private final AuthorizationService authorizationService;



    @PostMapping(path= "/login",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public JwtAuthorizationResponse authenticateUser(@RequestBody LoginRequest loginRequest){
        return authorizationService.login(loginRequest);
    }

    @PostMapping(path="/reset-password",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String>  resetPassword( @RequestBody ResetPasswordRequest request){
        authorizationService.sendPasswordResetLink(request.getEmailOrUsername());
        return ResponseEntity.ok("");
    }
    @PostMapping(path="change-password",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String>  changePassword(@RequestBody ChangePasswordRequest changePasswordRequest, @RequestParam String token)
    {
        authorizationService.changePassword(changePasswordRequest, token);

        return ResponseEntity.ok("");
    }

    @PostMapping(path = "update-password",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest,Principal connectedUser){
            authorizationService.changePassword(changePasswordRequest, connectedUser);
        return  ResponseEntity.ok("");
    }

}
