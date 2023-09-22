package com.enviro.envirobank.repository;

import com.enviro.envirobank.model.AccountTypes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTypeRepository extends JpaRepository<AccountTypes, Long> {
}
