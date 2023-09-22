package com.enviro.envirobank.service;

import com.enviro.envirobank.dto.PasswordResetRequest;
import com.enviro.envirobank.model.BankUser;
import com.enviro.envirobank.model.Customer;

public interface CustomerService {
    BankUser createNewCustomer(BankUser customer);

    BankUser updateCustomer(BankUser customer);

    Customer getCustomerByIdentityNumber(String customerIdentityNumber);

    BankUser setPassword(String name, String email, String token);

    BankUser resetPassword(PasswordResetRequest request);
    BankUser returnCustomer(Long id);
}
