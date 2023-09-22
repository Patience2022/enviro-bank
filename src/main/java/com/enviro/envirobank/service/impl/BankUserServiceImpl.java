package com.enviro.envirobank.service.impl;

import com.enviro.envirobank.model.BankUser;
import com.enviro.envirobank.repository.BankUserRepository;
import com.enviro.envirobank.service.BankUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class BankUserServiceImpl implements BankUserService {
    private final BankUserRepository bankUserRepository;
    @Override
    public BankUser getBankUser(Long id) {
        return bankUserRepository.findById(id).orElseThrow() ;
    }
}
