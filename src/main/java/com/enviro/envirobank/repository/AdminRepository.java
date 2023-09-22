package com.enviro.envirobank.repository;

import com.enviro.envirobank.model.Admin;
import com.enviro.envirobank.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
//    Optional<Admin> findByIdentityNumber(String identityNumber);
//    Optional<Admin> findAdminByEmail(String email);
}
