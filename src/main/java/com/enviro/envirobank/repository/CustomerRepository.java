package com.enviro.envirobank.repository;

import com.enviro.envirobank.model.BankUser;
import com.enviro.envirobank.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
//   Optional<Customer> findByIdentityNumber(String identityNumber);
//
//    Optional<Customer>findBankUserByEmail(String username);
//    Optional<Customer> findCustomerById(Long id);


}
