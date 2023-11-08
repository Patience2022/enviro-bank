package com.enviro.envirobank.service;

import com.enviro.envirobank.dto.CustomerDto;
import com.enviro.envirobank.dto.ListCustomerDto;
import com.enviro.envirobank.model.Customer;
import com.enviro.envirobank.repository.CustomerExp;

import java.util.List;

public interface CustomerService {
    Customer createNewCustomer(Customer customer);

    Customer updateCustomer(Customer customer, long id);

    Customer getCustomerByIdentityNumber(String customerIdentityNumber);


    Customer returnCustomer(Long id);

    List<CustomerExp> getAllCustomers();

    Customer getCustomerUsernameOrEmail(String data);
}
