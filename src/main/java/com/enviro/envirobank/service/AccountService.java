package com.enviro.envirobank.service;

import com.enviro.envirobank.dto.AccountsResponse;
import com.enviro.envirobank.model.Account;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    void withdraw(String accountNum, BigDecimal
            amountToWithdraw);

   AccountsResponse getAccounts(int pageNumber, int pageSize);

    Page<Account> getClientAccounts(long customerId,int pageNumber, int pageSize);
    Page<Account> retrieveAccounts(int pageNumber, int pageSize);
}
