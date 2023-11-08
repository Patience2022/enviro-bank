package com.enviro.envirobank.repository;

import com.enviro.envirobank.dto.TransactionDTO;
import com.enviro.envirobank.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByPendingTrue();
    List<Transaction> findByAccount_AccountNumber(String accountNumber);

    @Query(value = "     SELECT t.id as id, t.amount as amount, t.date as date, t.description as description, t.reference as reference, t.service_fee as serviceFee, t.type as transactionType,\n" +
            "        t.pending as pending, t.destination as destination, a.account_number as accountNumber, tt.name as transactionType\n" +
            "        FROM transactions as t\n" +
            "        join accounts as a on a.account_id = t.account\n" +
            "        join transaction_types as tt on tt.id = t.type where account_number =:account_number", nativeQuery = true)
    List<TransactionDTO>getTransactions(@Param("account_number") String account_number);
}
