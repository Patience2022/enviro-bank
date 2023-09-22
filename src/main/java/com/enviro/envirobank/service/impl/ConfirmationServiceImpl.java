package com.enviro.envirobank.service.impl;

import com.enviro.envirobank.model.LoginConfirmation;
import com.enviro.envirobank.repository.ConfirmationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConfirmationServiceImpl {

    private ConfirmationRepository confirmationRepository;

    public String saveToken(LoginConfirmation token){
        LoginConfirmation savedToken = confirmationRepository.save(token);
        return savedToken.getToken();
    }
}
