package com.enviro.envirobank.repository;

import com.enviro.envirobank.dto.ListCustomerDto;
import com.enviro.envirobank.model.BankUser;
import com.enviro.envirobank.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
Optional<Customer> findByEmail(String email);


@Query(value = "SELECT  b.first_name as firstName, b.last_name as lastName, b.email as email, count(a.account_number)as numberOfAccounts " +
        "FROM public.bank_users as b " +
        "join public.accounts as a on a.customer = b.id " +
        "group by b.id, b.first_name,b.last_name,b.email ", nativeQuery = true)
List<CustomerExp> findAllCustomers();
//       "join public.customers as c on b.id = c.id " +

}
