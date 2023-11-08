package com.enviro.envirobank.repository;

import com.enviro.envirobank.model.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankUserRepository extends JpaRepository<BankUser, Long> {

    Optional<BankUser> findByIdentityNumber(String customerIdentityNumber);
    Optional<BankUser> findByEmail(String email);
    Optional<BankUser> findByUserNameOrEmail(String username, String email);
    Optional<BankUser> findByUserName(String username);
    Boolean existsByUserName(String username);
    Boolean existsByEmail(String email);
    Boolean existsByIdentityNumber(String identityNumber);

}
