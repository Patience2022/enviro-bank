package com.enviro.envirobank.service;

import com.enviro.envirobank.model.TransactionType;

public interface TransactionTypeService {
    TransactionType findTransactionTypeByName(String name);
}
