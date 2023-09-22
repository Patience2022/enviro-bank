package com.enviro.envirobank.repository;

import com.enviro.envirobank.model.Account;
import com.enviro.envirobank.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String accountNumber);
   Page<Account> findByCustomer(Customer customer, Pageable pageable);
}
