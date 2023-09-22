package com.enviro.envirobank.repository;

import com.enviro.envirobank.model.LoginConfirmation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfirmationRepository extends JpaRepository<LoginConfirmation, Long> {
    Optional<LoginConfirmation> findLoginConfirmationByToken(String token);
}
