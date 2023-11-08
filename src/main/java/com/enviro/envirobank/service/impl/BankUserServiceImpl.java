package com.enviro.envirobank.service.impl;

import com.enviro.envirobank.dto.ChangePasswordRequest;
import com.enviro.envirobank.exception.ResourceNotFoundException;
import com.enviro.envirobank.mail.EmailSender;
import com.enviro.envirobank.model.BankUser;
import com.enviro.envirobank.repository.BankUserRepository;
import com.enviro.envirobank.service.BankUserService;
import com.enviro.envirobank.utils.TokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BankUserServiceImpl implements BankUserService {
    private final BankUserRepository repository;


    @Override
    public String id(String username) {
        BankUser user = repository.findByUserNameOrEmail(username, username).orElseThrow();
        Long id = user.getId();
        return id.toString();
    }

    @Override
    public BankUser findByUsernameOremail(String username) {
        return repository.findByUserNameOrEmail(username, username).orElseThrow();
    }
}