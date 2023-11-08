package com.enviro.envirobank.service;

import com.enviro.envirobank.dto.AccountsResponse;
import com.enviro.envirobank.dto.TransferRequest;
import com.enviro.envirobank.dto.WithdrawalResponse;
import com.enviro.envirobank.model.Account;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    WithdrawalResponse withdraw(String accountNum, BigDecimal
            amountToWithdraw);

    Page<Account> getAccounts(int pageNumber, int pageSize);

    Page<Account> getClientAccounts(long customerId,int pageNumber, int pageSize);

    void interTransfer(TransferRequest transferRequest);
    List<Account> getAccounts();

    void updateAccount(Account accountFrom);

    Account getAccountByAccountNumber(String accountNumber);
}
