package com.enviro.envirobank.service.impl;
import com.enviro.envirobank.dto.AccountsResponse;
import com.enviro.envirobank.exception.ResourceNotFoundException;
import com.enviro.envirobank.model.Account;
import com.enviro.envirobank.model.AccountTypes;
import com.enviro.envirobank.model.Customer;
import com.enviro.envirobank.repository.AccountRepository;
import com.enviro.envirobank.repository.AccountTypeRepository;
import com.enviro.envirobank.repository.CustomerRepository;
import com.enviro.envirobank.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void withdraw(String accountNum, BigDecimal amountToWithdraw) {
        Account account = accountRepository.findByAccountNumber(accountNum).orElseThrow(() ->
                new ResourceNotFoundException("Account", "account number", accountNum));
        AccountTypes accountType = accountTypeRepository.findById(account.getAccountType().getId())
                .orElseThrow(() -> new RuntimeException("Account type not found"));
        if (accountType.getId() == account.getAccountType().getId()) {
            if (accountType.getAccountTypeName().equals("Savings")) {
                savingsWithdrawal(amountToWithdraw, account);
            } else {
                currentWithdrawal(amountToWithdraw, account);
            }
        } else {
            throw new ResourceNotFoundException("Account", "account number", accountNum);
        }
    }

    @Override
    public AccountsResponse getAccounts(int pageNumber, int pageSize) {
        return null;
    }

    public Page<Account> retrieveAccounts(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Account> accounts = accountRepository.findAll(pageable);
        return accounts;
    }

    @Override
    public Page<Account> getClientAccounts(long customerId,int pageNumber, int pageSize) {
        Customer customer =customerRepository.findById(customerId).orElseThrow();
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Account> accounts= accountRepository.findByCustomer(customer,pageable);
        return accounts;
    }


    private void currentWithdrawal(BigDecimal amountToWithdraw, Account account) {
        if (account.getBalance().add(account.getOverdraft()).compareTo(amountToWithdraw) >= 0) {
            account.setBalance(account.getBalance().subtract(amountToWithdraw));
            account.setAmountAvailable(account.getAmountAvailable().subtract(amountToWithdraw));
            accountRepository.save(account);
            System.out.println("Withdrawal was successful. Your new balance is R " + account.getBalance() +
                    " and amount available to withdraw is R " + account.getAmountAvailable());
        } else {
            System.out.println("You don't have insufficient funds to perform this operation");
        }
    }

    private void savingsWithdrawal(BigDecimal amountToWithdraw, Account account) {
        if (account.getBalance().subtract(amountToWithdraw).compareTo(account.getAccountType().getMinimumAmountRequired()) >= 0) {
            account.setBalance(account.getBalance().subtract(amountToWithdraw));
            account.setAmountAvailable(account.getAmountAvailable().subtract(amountToWithdraw));
            accountRepository.save(account);
            System.out.println("Withdrawal was successful. Your new balance is R " + account.getBalance() +
                    " and amount available to withdraw is R " + account.getAmountAvailable());
        } else {
            System.out.println("You don't have insufficient funds to perform this operation");
        }
    }
}
