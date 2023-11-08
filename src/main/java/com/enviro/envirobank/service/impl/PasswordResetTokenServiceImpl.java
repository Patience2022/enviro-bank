package com.enviro.envirobank.service.impl;

import com.enviro.envirobank.exception.ResourceNotFoundException;
import com.enviro.envirobank.model.BankUser;
import com.enviro.envirobank.model.PasswordResetToken;
import com.enviro.envirobank.repository.PasswordResetTokenRepository;
import com.enviro.envirobank.service.PasswordResetTokenService;
import com.enviro.envirobank.utils.TokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@AllArgsConstructor
@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {

    private final PasswordResetTokenRepository repository;

    @Override
    public PasswordResetToken getToken(String token) {
        return repository.findByToken(token).orElseThrow(
                ()-> new ResourceNotFoundException("Token", "value ", token));
    }

    @Override
    public void deleteToken(String token) {
        PasswordResetToken passwordResetToken =repository.findByToken(token).orElseThrow(
                ()-> new ResourceNotFoundException("Token", "value ", token));
        repository.delete(passwordResetToken);
    }

    @Override
    public PasswordResetToken createNewToken(PasswordResetToken token) {
        return repository.save(token);
    }

    @Override
    public PasswordResetToken getTokenByUser(BankUser bankUser) {
        return repository.findByBankUser(bankUser).orElseThrow(
                ()-> new RuntimeException("No token found")
        );
    }
}
