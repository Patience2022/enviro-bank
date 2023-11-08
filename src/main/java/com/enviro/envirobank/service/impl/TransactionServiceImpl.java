package com.enviro.envirobank.service.impl;

import com.enviro.envirobank.dto.TransactionDTO;
import com.enviro.envirobank.model.Account;
import com.enviro.envirobank.model.Transaction;
import com.enviro.envirobank.repository.TransactionRepository;
import com.enviro.envirobank.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    @Override
    public void addTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> findAllPending() {
        return transactionRepository.findByPendingTrue();
    }

    @Override
    public void updateTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public List<TransactionDTO> getAccountTransations(String accountNumber) {
        return transactionRepository.getTransactions(accountNumber);
    }


}
