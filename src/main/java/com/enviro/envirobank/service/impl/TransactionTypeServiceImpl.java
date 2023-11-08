package com.enviro.envirobank.service.impl;

import com.enviro.envirobank.exception.ResourceNotFoundException;
import com.enviro.envirobank.model.TransactionType;
import com.enviro.envirobank.repository.TransactionTypeRepository;
import com.enviro.envirobank.service.TransactionTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionTypeServiceImpl implements TransactionTypeService {
    private final TransactionTypeRepository typeRepository;
    @Override
    public TransactionType findTransactionTypeByName(String name) {
        return typeRepository.findByName(name).orElseThrow(()->
                new ResourceNotFoundException("Transaction ", "type ", name));
    }
}
