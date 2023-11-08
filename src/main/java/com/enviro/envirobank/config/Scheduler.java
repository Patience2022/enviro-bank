package com.enviro.envirobank.config;


import com.enviro.envirobank.model.Account;
import com.enviro.envirobank.model.Transaction;
import com.enviro.envirobank.service.AccountService;
import com.enviro.envirobank.service.TransactionService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class Scheduler {

    private final TransactionService transactionService;
    private final AccountService accountService;

//    @Scheduled(cron = "0/60 * * * * *")
    public void updateAccounts(){
        List<Transaction> transactions = transactionService.findAllPending();
        transactions.forEach(transaction -> {
            transaction.setPending(false);
            transactionService.updateTransaction(transaction);
            Account accountFrom = transaction.getAccount();
            accountFrom.setBalance(accountFrom.getBalance().subtract(transaction.getAmount()));
            accountService.updateAccount(accountFrom);

            Account accountTo = accountService.getAccountByAccountNumber(transaction.getDestination());
            accountTo.setBalance(accountTo.getBalance().add(transaction.getAmount()));
            accountTo.setAmountAvailable(accountTo.getAmountAvailable().add(transaction.getAmount()));
            accountService.updateAccount(accountTo);
        });
    }







}
