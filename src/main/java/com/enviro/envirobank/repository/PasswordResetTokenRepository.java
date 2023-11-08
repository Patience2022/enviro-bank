package com.enviro.envirobank.repository;

import com.enviro.envirobank.model.BankUser;
import com.enviro.envirobank.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
  Optional<PasswordResetToken> findByToken(String token);
  Optional<PasswordResetToken> findByBankUser(BankUser bankUser);
}
