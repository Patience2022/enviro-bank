package com.enviro.envirobank.repository;

import com.enviro.envirobank.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> {
    Optional<TransactionType>findByName(String name);

}
