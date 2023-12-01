package com.enviro.envirobank.service.impl;

import com.enviro.envirobank.dto.TransferRequest;
import com.enviro.envirobank.dto.WithdrawalResponse;
import com.enviro.envirobank.exception.ResourceNotFoundException;
import com.enviro.envirobank.model.Account;
import com.enviro.envirobank.model.AccountTypes;
import com.enviro.envirobank.model.Customer;
import com.enviro.envirobank.model.Transaction;
import com.enviro.envirobank.repository.AccountRepository;
import com.enviro.envirobank.repository.AccountTypeRepository;
import com.enviro.envirobank.repository.CustomerRepository;
import com.enviro.envirobank.service.AccountService;
import com.enviro.envirobank.service.TransactionService;
import com.enviro.envirobank.service.TransactionTypeService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final CustomerRepository customerRepository;
    private final TransactionTypeService transactionTypeService;
    private final TransactionService transactionService;

    private final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Override
    public WithdrawalResponse withdraw(String accountNum, BigDecimal amountToWithdraw) {
        Account account = accountRepository.findByAccountNumber(accountNum).orElseThrow(() ->
                new ResourceNotFoundException("Account", "account number", accountNum));
        AccountTypes accountType = accountTypeRepository.findById(account.getAccountType().getId())
                .orElseThrow(() -> new RuntimeException("Account type not found"));
        if (accountType.getId() == account.getAccountType().getId()) {
            if (account.getAmountAvailable().compareTo(amountToWithdraw) >= 0) {
                account.setBalance(account.getBalance().subtract(amountToWithdraw));
                account.setAmountAvailable(account.getAmountAvailable().subtract(amountToWithdraw));

                Transaction transaction = new Transaction();
                transaction.setAccount(account);
                transaction.setDate(Timestamp.valueOf(LocalDateTime.now()));
                transaction.setDestination(account.getAccountNumber());
                transaction.setTransactionType(transactionTypeService.findTransactionTypeByName("Withdrawal"));
                transaction.setAmount(amountToWithdraw);
                transaction.setDescription("Withdrawal");
                transactionService.addTransaction(transaction);

                accountRepository.save(account);
            } else {
                throw new RuntimeException("You have insufficient balance to perform this operation." +
                        " Amount available to withdraw is R" + account.getAmountAvailable());
            }
        } else {
            throw new ResourceNotFoundException("Account", "account number", accountNum);
        }
        WithdrawalResponse withdrawalResponse = new WithdrawalResponse();
        withdrawalResponse.setAmountWithdrawn(amountToWithdraw);
        withdrawalResponse.setAccountBalance(account.getBalance());
        withdrawalResponse.setAmountAvailableForWithdrawal(account.getAmountAvailable());

        return withdrawalResponse;
    }

    @PreAuthorize("hasRole('Admin')")
    @Override
    public Page<Account> getAccounts(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return accountRepository.findAll(pageable);
    }

    @Override
    public Page<Account> getClientAccounts(long customerId,int pageNumber, int pageSize) {
        Customer customer =customerRepository.findById(customerId).orElseThrow();
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return accountRepository.findByCustomer(customer,pageable);
    }

   @Transactional
    @Override
    public void interTransfer(TransferRequest transferRequest) {
        Account accountFrom = accountRepository.findByAccountNumber(transferRequest.getFromAccountNumber())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Account", "account number", transferRequest.getFromAccountNumber()));
        Account accountTo = accountRepository.findByAccountNumber(transferRequest.getToAccountNumber())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Account", "account number", transferRequest.getToAccountNumber()));
        if (accountFrom.getCustomer() == accountTo.getCustomer() && !Objects.equals(
                accountFrom.getAccountNumber(), accountTo.getAccountNumber())) {
            if (accountFrom.getAmountAvailable().compareTo(transferRequest.getTransferAmount()) >= 0) {

                accountFrom.setAmountAvailable(accountFrom.getAmountAvailable().subtract(transferRequest.getTransferAmount()));
                accountRepository.save(accountFrom);

                Transaction transaction = new Transaction();
                transaction.setAccount(accountFrom);
                transaction.setDate(Timestamp.valueOf(LocalDateTime.now()));
                transaction.setDestination(transferRequest.getToAccountNumber());
                if(transferRequest.getReference()==null){
                    transaction.setReference( accountTo.getAccountNumber());
                }else{
                    transaction.setReference(transferRequest.getReference() + "   " + accountTo.getAccountNumber());
                }
                transaction.setTransactionType(transactionTypeService.findTransactionTypeByName("inter-transfer"));
                transaction.setAmount(transferRequest.getTransferAmount());
                transaction.setDescription("inter-transfer");
                transactionService.addTransaction(transaction);

            } else{
                throw new RuntimeException("Insufficient funds to perform this transaction");
            }
        } else {
            throw new RuntimeException("Incorrect details provided");}
    }

    @Override
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public void updateAccount(Account accountFrom) {
        accountRepository.save(accountFrom);
    }

    @Override
    public Account getAccountByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber).orElseThrow();
    }












   /* @Async
    @Scheduled(fixedDelayString = "PT1M")
    public void setBalance(Account account,BigDecimal amount){
         account.setBalance(account.getBalance().subtract(amount));
        System.out.println("I am delayed");
    }*/

//    private void currentWithdrawal(BigDecimal amountToWithdraw, Account account) {
//        if (account.getAmountAvailable().compareTo(amountToWithdraw) >= 0) {
//            account.setBalance(account.getBalance().subtract(amountToWithdraw));
//            account.setAmountAvailable(account.getAmountAvailable().subtract(amountToWithdraw));
//            accountRepository.save(account);
//        } else {
//            throw new RuntimeException("You have insufficient balance to perform this operation." +
//                    " Amount available to withdraw is R"+account.getAmountAvailable());
//        }
//    }
//
//    private void savingsWithdrawal(BigDecimal amountToWithdraw, Account account) {
//        if (account.getAmountAvailable().compareTo(amountToWithdraw) >= 0) {
//            account.setBalance(account.getBalance().subtract(amountToWithdraw));
//            account.setAmountAvailable(account.getAmountAvailable().subtract(amountToWithdraw));
//            accountRepository.save(account);
//        } else {
//            throw new RuntimeException("You have insufficient balance to perform this operation." +
//                    " Amount available to withdraw is R"+account.getAmountAvailable());
//        }
//    }
}
