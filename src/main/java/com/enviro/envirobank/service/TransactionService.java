package com.enviro.envirobank.service;

import com.enviro.envirobank.dto.TransactionDTO;
import com.enviro.envirobank.model.Account;
import com.enviro.envirobank.model.Transaction;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public interface TransactionService {
    void addTransaction(Transaction transaction);

List<Transaction> findAllPending();

    void updateTransaction(Transaction transaction);
    List<TransactionDTO> getAccountTransations(String accountNumber);
}
